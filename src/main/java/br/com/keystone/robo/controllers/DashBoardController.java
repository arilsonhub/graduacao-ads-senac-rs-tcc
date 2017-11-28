package br.com.keystone.robo.controllers;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.keystone.robo.annotations.ALoteValidate;
import br.com.keystone.robo.annotations.ARoles;
import br.com.keystone.robo.business.exceptions.DevedorJaFoiProcessadoException;
import br.com.keystone.robo.business.exceptions.LoteNaoPossuiDevedoresParaProcessarException;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.core.exceptions.VraptorValidatorException;
import br.com.keystone.robo.helper.ViewHelper;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.repository.IDevProcessadoLoteTempRepository;
import br.com.keystone.robo.repository.ILoteRepository;
import br.com.keystone.robo.service.IGeracaoLoteService;
import br.com.keystone.robo.validation.IValidation;
import br.com.keystone.robo.vo.CampanhaVO;
import br.com.keystone.robo.vo.DadosGeracaoLoteVO;

@Controller
public class DashBoardController {

	@Inject
	private Result result;
	
	@Inject
	private IGeracaoLoteService geracaoLoteService;
	
	@Inject
	private ILoteRepository loteRepository;
	
	@Inject
	private IDevProcessadoLoteTempRepository devProcessadoLoteTempRepository;
	
	@Inject @ALoteValidate
	private IValidation<DadosGeracaoLoteVO, VraptorValidatorException> loteValidate;
	
	@Get
	@Path("/dashboard")
	@ARoles(roles={1},isAllRolesRequired=true)
	public void visualizarDashboard(){
		result.forwardTo(ViewHelper.setViewPath("dashboard","dashBoardOperadorRobo"));
	}
	
	@Post
	@Path("/buscar-campanhas")
	@ARoles(roles={1},isAllRolesRequired=true)
	public void buscarCampanhas(){
		try{
			List<CampanhaVO> listaCampanhas =  geracaoLoteService.buscarCampanhas();
			result.use(Results.json()).from(listaCampanhas).serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}
	}
	
	@Post
	@Path("/gerar-lote")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void gerarLoteGeracaoBoleto(DadosGeracaoLoteVO dadosGeracaoLoteVO){
		try {			
			loteValidate.execute(dadosGeracaoLoteVO);
			geracaoLoteService.gerarLoteGeracaoBoleto(dadosGeracaoLoteVO);
			result.use(Results.json()).from(new String("Lote gerado com sucesso")).serialize();

		} catch (VraptorValidatorException vee) {			
			vee.getValidator().onErrorUse(Results.json()).from(vee.getValidator().getErrors()).serialize();
			
		}catch(DevedorJaFoiProcessadoException djfpe){
			result.use(Results.json()).from(djfpe).include("lote").serialize();
			
		}catch (IntranetException ie) {
			result.use(Results.json()).from(ie).serialize();			
		} 
	}
	
	@Post
	@Path("/listar-lotes")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void listarLotes(Date lot_datainclusao){
		try{			
			result.use(Results.json()).from(loteRepository.getLotesByDataInclusao(lot_datainclusao)).serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/lote-erros")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void listarErrosDoLote(Lote lote, Integer page){
		try{			
			result.use(Results.json()).from(loteRepository.getLoteByIdWithErrors(lote, page)).recursive().serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/parar-lote")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void pausarLoteEmAndamento(Lote lote){
		try{			
			geracaoLoteService.pausarGeracaoLote(lote);
			result.use(Results.json()).from(new String("Geração do lote pausada com sucesso")).serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/retomar-lote")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void retomarLoteEmPausa(Lote lote){
		try{			
			geracaoLoteService.retomarGeracaoLote(lote);
			result.use(Results.json()).from(new String("Retomando geração do lote")).serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/marcar-devedor")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void marcarDevedor(Devedor devedor, Integer dlt_valido, Lote lote){
		try{
			devProcessadoLoteTempRepository.marcarProcessamentoDevedor(devedor, dlt_valido, lote);
			result.use(Results.json()).from(Boolean.TRUE).serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/confirmar-geracao-lote")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void confirmarGeracaoLote(Lote lote, String acao){
		try {
			Integer acaoSolicitada = -1;
			lote = loteRepository.getLoteByIdWithDevedores(lote,0);
			if(acao != null && !acao.equals("")) acaoSolicitada = Integer.parseInt(acao); 
			geracaoLoteService.confirmarGeracaoLote(lote,acaoSolicitada);
			result.use(Results.json()).from(new String("Lote gerado com sucesso")).serialize();
		}
		catch(LoteNaoPossuiDevedoresParaProcessarException lnpdppe){
			result.use(Results.json()).from(lnpdppe).serialize();
		}catch (IntranetException ie) {
			result.use(Results.json()).from(ie).serialize();
		}			
	}
	
	@Post
	@Path("/exibir-devedores-ja-processados")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void exibirDevedoresJaProcessadosDoLote(Lote lote, Integer page){
		try{			
			result.use(Results.json()).from(devProcessadoLoteTempRepository.getDevedoresJaProcessadosByLote(lote, page)).recursive().serialize();
		}catch(IntranetException ie){
			result.use(Results.json()).from(ie).serialize();
		}		
	}
	
	@Post
	@Path("/cancelar-lote")
	@ARoles(roles={1},isAllRolesRequired=true)
	@Consumes("application/json")
	public void cancelarGeracaoLote(Lote lote){
		try {			
			geracaoLoteService.cancelarGeracaoLote(lote);
			result.use(Results.json()).from(new String("Lote cancelado com sucesso")).serialize();
		} catch (IntranetException ie) {
			result.use(Results.json()).from(ie).serialize();
		}			
	}
}