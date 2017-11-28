package br.com.keystone.robo.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.keystone.robo.annotations.ADevedorDAOWebService;
import br.com.keystone.robo.business.exceptions.DevedorJaFoiProcessadoException;
import br.com.keystone.robo.business.exceptions.LoteNaoPossuiDevedoresParaProcessarException;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.LoteStatusEnum;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.model.LoteStatus;
import br.com.keystone.robo.repository.ICampanhaRepository;
import br.com.keystone.robo.repository.IDevProcessadoLoteTempRepository;
import br.com.keystone.robo.repository.IDevedorRepository;
import br.com.keystone.robo.repository.ILoteRepository;
import br.com.keystone.robo.repository.ILoteStatusRepository;
import br.com.keystone.robo.repository.IUsuarioRepository;
import br.com.keystone.robo.session.SessaoUsuario;
import br.com.keystone.robo.vo.CampanhaVO;
import br.com.keystone.robo.vo.DadosGeracaoLoteVO;

public class GeracaoLoteService implements IGeracaoLoteService {
	
	@Inject
	private ICampanhaRepository campanhaRepository;
	
	@Inject @ADevedorDAOWebService
	private IDevedorRepository devedorRepository;
	
	@Inject
	private ILoteStatusRepository loteStatusRepository;
	
	@Inject
	private ILoteRepository loteRepository;
	
	@Inject
	private IGeracaoBoletoService geracaoBoletoService;
	
	@Inject 
	private SessaoUsuario sessaoUsuario;
	
	@Inject 
	private IUsuarioRepository usuarioRepository;
	
	@Inject
	private IEnvioEmailLoteService envioEmailLoteService;
	
	@Inject
	private IDevProcessadoLoteTempRepository devProcessadoLoteRepository;

	@Override
	public List<CampanhaVO> buscarCampanhas() throws IntranetException {
		return campanhaRepository.buscarCampanhas();
	}

	@Override
	public void gerarLoteGeracaoBoleto(DadosGeracaoLoteVO dadosGeracaoLoteVO) throws IntranetException, DevedorJaFoiProcessadoException {
		
		Lote lote = new Lote();		
		lote.setLot_campanhacodigo(dadosGeracaoLoteVO.getLot_campanhacodigo());
		lote.setLot_datainclusao(new Date());
		lote.setLot_datapagamento(dadosGeracaoLoteVO.getLot_datapagamento());
		lote.setLot_datavencimento(dadosGeracaoLoteVO.getLot_datavencimento());		
		lote.setLot_descontopercentual(dadosGeracaoLoteVO.getLot_descontopercentual());
		lote.setLot_descontohonorarios(dadosGeracaoLoteVO.getLot_descontohonorarios());
		lote.setUsuario(usuarioRepository.carregarUsuarioById(sessaoUsuario.getUsu_cod()));
		lote.setLot_valido(1);
		
		LoteStatus loteStatus = new LoteStatus();
		loteStatus.setLts_cod(LoteStatusEnum.EmAndamento.getChave());
		lote.setLoteStatus(loteStatusRepository.getLoteStatusById(loteStatus));
		
		CampanhaVO campanhaVO = new CampanhaVO();
		campanhaVO.setFil_cod(dadosGeracaoLoteVO.getLot_campanhacodigo());
		
		List<Devedor> listaDevedores =  devedorRepository.buscarDevedoresByCampanha(campanhaVO);		
		loteRepository.gravarLote(lote,listaDevedores);		
		geracaoBoletoService.execute(lote);
	}

	@Override
	public void retomarGeracaoLote(Lote lote) throws IntranetException {
		if(loteRepository.isLoteEmStatus(lote, LoteStatusEnum.Pausado.getChave())){
			lote = loteRepository.alterarStatusLote(lote, LoteStatusEnum.EmAndamento.getChave());
			lote = loteRepository.getLoteComDevedoresNaoProcessados(lote);
			geracaoBoletoService.execute(lote);
			envioEmailLoteService.enviarEmailAposLoteRetomado(lote);
		}else{
			throw new IntranetException("Este lote não esta em pausa");
		}
	}
	
	@Override
	public void pausarGeracaoLote(Lote lote) throws IntranetException {
		if(loteRepository.isLoteEmStatus(lote, LoteStatusEnum.EmAndamento.getChave())){
			loteRepository.alterarStatusLote(lote, LoteStatusEnum.Pausado.getChave());
		}else{
			throw new IntranetException("Este lote não esta em andamento");
		}
	}

	@Override
	public void confirmarGeracaoLote(Lote lote, Integer acao) throws IntranetException, LoteNaoPossuiDevedoresParaProcessarException {
		
		switch(acao){
			 case 1:
				 devProcessadoLoteRepository.removerDevedoresDoLoteComMarcacao(1, lote);				 
			 break;
			 
			 case 2:
				 devProcessadoLoteRepository.removerDevedoresDoLoteComMarcacao(0, lote);				 
			 break;
				 
			 case 3:
				 devProcessadoLoteRepository.marcarTodosDevedoresJaProcessadosDoLote(lote);
			 break;
				 
			 default:
				 throw new IntranetException("Ação inválida");			 
		}
		
		devProcessadoLoteRepository.colocarDevedoresNoLote(lote);
		devProcessadoLoteRepository.removerDevedoresDaTabelaTemporaria(lote);
		
		if(loteRepository.isLotePossuiDevedoresParaProcessar(lote)){
			lote.setLot_valido(1);
			loteRepository.atualizarLote(lote);
			geracaoBoletoService.execute(lote);
		}else{
			loteRepository.removerLote(lote);
			throw new LoteNaoPossuiDevedoresParaProcessarException("Não há devedores para acionar no lote, o lote foi cancelado");
		}
	}
	
	@Override
	public void cancelarGeracaoLote(Lote lote) throws IntranetException {
		
		devProcessadoLoteRepository.removerDevedoresDaTabelaTemporariaComCommit(lote);
		loteRepository.removerLoteComDependencias(lote);		
	}
}