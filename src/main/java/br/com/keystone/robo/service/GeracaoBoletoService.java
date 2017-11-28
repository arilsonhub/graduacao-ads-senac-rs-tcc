package br.com.keystone.robo.service;

import java.util.Date;

import javax.inject.Inject;

import br.com.keystone.robo.annotations.AdevedorDAOJPA;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.ConfiguracaoEnum;
import br.com.keystone.robo.enums.LoteStatusEnum;
import br.com.keystone.robo.helper.DateHelper;
import br.com.keystone.robo.model.Configuracao;
import br.com.keystone.robo.model.ControleLoteErro;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Divida;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.model.Parcela;
import br.com.keystone.robo.repository.IConfiguracaoRepository;
import br.com.keystone.robo.repository.IControleLoteErroRepository;
import br.com.keystone.robo.repository.IDevedorRepository;
import br.com.keystone.robo.repository.ILoteRepository;
import br.com.keystone.robo.vo.DadosRequestServicoGeracaoBoletosVO;
import br.com.keystone.robo.vo.DadosResponseServicoGeracaoBoletosVO;

public class GeracaoBoletoService implements IGeracaoBoletoService, Runnable {
	
	@Inject
	private IServicoGeracaoBoletoWebService servicoGeracaoBoletoWebService;
	
	@Inject
	private IConfiguracaoRepository configuracaoRepository;
	
	@Inject
	private IControleLoteErroRepository controleLoteErroRepository;
	
	@Inject
	private ILoteRepository loteRepository;
	
	@Inject @AdevedorDAOJPA 
	private IDevedorRepository devedorRepository;
	
	@Inject
	private IEnvioEmailLoteService envioEmailLoteService;
	
	private String urlServico;
	
	private Lote lote;
	
	@Override
	public void execute(Lote lote) throws IntranetException {		
		this.lote = lote;
		
		Configuracao configuracao = new Configuracao();
		configuracao.setCon_cod(ConfiguracaoEnum.ServicoGeracaoBoletos.getConfiguracaoChave());
		try {
			configuracao = configuracaoRepository.getConfiguracaoById(configuracao);
			this.urlServico = configuracao.getCon_valor();
		} catch (IntranetException e) {
			this.urlServico = null;
		}
		
		new Thread(this).start();		
	}

	@Override
	public void run() {
		Boolean isLoteComErros = Boolean.FALSE;
		Boolean isLoteEmPausa  = Boolean.FALSE;
		try {				
			for(Devedor devedor : lote.getDevedores()){
				if(!loteRepository.isLoteEmStatus(lote, LoteStatusEnum.Pausado.getChave())){
					devedor = devedorRepository.getDevedorById(devedor);
					DadosRequestServicoGeracaoBoletosVO request = montarRequest(devedor);
					if(tratarResponse(servicoGeracaoBoletoWebService.gerarBoletoParaDevedor(request,this.urlServico))){						
						devedor.setDev_processado(1);
						devedorRepository.atualizarDevedor(devedor);
					}else{
						isLoteComErros = Boolean.TRUE;
					}
				}else{
					isLoteEmPausa = Boolean.TRUE;
					break;
				}				
			}
		} catch (Exception e) {
			DadosResponseServicoGeracaoBoletosVO response = new DadosResponseServicoGeracaoBoletosVO();
			response.setDescricao(e.getMessage());
			response.setDatahora(new Date());
			response.setProcesso("Processo de geração de boleto");
			tratarResponse(response);
			isLoteComErros = Boolean.TRUE;
		}finally{
			tratamentoAposGeracaoBoletosDoLote(lote,isLoteComErros,isLoteEmPausa);			
		}
	}
	
	private void tratamentoAposGeracaoBoletosDoLote(Lote lote,Boolean isLoteComErros, Boolean isLoteEmPausa){
		
		try {
			if(!isLoteEmPausa){
				if(isLoteComErros){
					lote = loteRepository.alterarStatusLote(lote, LoteStatusEnum.FalhaNoLote.getChave());
					
				}else {
					
					lote = loteRepository.alterarStatusLote(lote, LoteStatusEnum.LoteFinalizadoComSucesso.getChave());
				}
				
				envioEmailLoteService.enviarEmailAposLoteFinalizado(lote);
				
			}else{
				envioEmailLoteService.enviarEmailAposLotePausado(lote);
			}
			
		} catch (IntranetException e) {				
			e.printStackTrace();
		}
	}
	
	private Boolean tratarResponse(DadosResponseServicoGeracaoBoletosVO response){
		Boolean sucesso = (response.getDescricao().equalsIgnoreCase("ok") ? Boolean.TRUE : Boolean.FALSE);		
		if(!sucesso){
			ControleLoteErro controleLoteErro = new ControleLoteErro();
			controleLoteErro.setClt_datahora(response.getDatahora());
			controleLoteErro.setClt_descricao(response.getDescricao());
			controleLoteErro.setClt_processo(response.getProcesso());
			controleLoteErro.setLote(this.lote);
			controleLoteErroRepository.inserirErro(controleLoteErro);
		}
		return sucesso;
	}
	
	private DadosRequestServicoGeracaoBoletosVO montarRequest(Devedor devedor) throws IntranetException {
		
		String lot_datapagamento     = null;
		String lot_datavencimento    = null;		
		String[] dataPagto           = null;
		String[] descontoPercentual  = null;
		String[] descontoHonorarios  = null;
		
		if(this.lote.getLot_datapagamento() != null)  lot_datapagamento  = DateHelper.formatDateIsoToBR(this.lote.getLot_datapagamento());		
		if(this.lote.getLot_datavencimento() != null) lot_datavencimento = DateHelper.formatDateIsoToBR(this.lote.getLot_datavencimento());
		
		DadosRequestServicoGeracaoBoletosVO request = new DadosRequestServicoGeracaoBoletosVO();
		String[] div_Cod = new String[devedor.getDividas().size()];
		Integer i = 0;
		for(Divida divida : devedor.getDividas()){
			div_Cod[i] = divida.getDiv_cod().toString();
			
			String[] par_Cod = new String[divida.getParcelas().size()];
			
			if(lot_datapagamento != null) {
				dataPagto = new String[divida.getParcelas().size()];	
			}
			
			if(this.lote.getLot_descontopercentual() != null){
				descontoPercentual = new String[divida.getParcelas().size()];
			}
			if(this.lote.getLot_descontohonorarios() != null){
				descontoHonorarios = new String[divida.getParcelas().size()];
			}			
			String[] par_Nu    = new String[divida.getParcelas().size()];
			
			Integer j = 0;
			for(Parcela parcela : divida.getParcelas()){
				par_Cod[j] = parcela.getPar_cod().toString();
				par_Nu[j]  = parcela.getPar_nu().toString();				
				
				if(dataPagto != null){
					dataPagto[j] = lot_datapagamento;
				}
				
				if(descontoPercentual != null){
					descontoPercentual[j] = this.lote.getLot_descontopercentual();				
				}
				
				if(descontoHonorarios != null){
					descontoHonorarios[j] = this.lote.getLot_descontohonorarios();				
				}
				
				j++;
			}
			
			if(lot_datapagamento != null) request.setDataPagto(dataPagto);
			request.setDiv_Cod(div_Cod);
			request.setPar_Cod(par_Cod);
			request.setPar_Nu(par_Nu);
			request.setDivCodDivida(div_Cod);
			i++;
		}
		
		request.setDataVencimentoSelecionada(lot_datavencimento);
		request.setDescPercentual(descontoPercentual);
		request.setDescHonorarios(descontoHonorarios);
		return request;
	}
}