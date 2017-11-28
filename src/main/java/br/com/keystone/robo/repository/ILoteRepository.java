package br.com.keystone.robo.repository;

import java.util.Date;
import java.util.List;

import br.com.keystone.robo.business.exceptions.DevedorJaFoiProcessadoException;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.vo.ControleLoteErroVO;
import br.com.keystone.robo.vo.DadosLoteVO;
import br.com.keystone.robo.vo.PaginatorVO;

public interface ILoteRepository {

	public void gravarLote(Lote lote, List<Devedor> listaDevedores) throws IntranetException, DevedorJaFoiProcessadoException;
	
	public Boolean isLoteEmStatus(Lote lote, Long lts_cod) throws IntranetException;
	
	public void atualizarLote(Lote lote) throws IntranetException;
	
	public Lote getLoteById(Lote lote) throws IntranetException;
	
	public Lote getLoteComDevedoresNaoProcessados(Lote lote) throws IntranetException;
	
	public Lote alterarStatusLote(Lote lote,Long lts_cod) throws IntranetException;
	
	public List<DadosLoteVO> getLotesByDataInclusao(Date lot_datainclusao) throws IntranetException;
	
	public PaginatorVO<ControleLoteErroVO> getLoteByIdWithErrors(Lote lote, Integer page) throws IntranetException;
	
	public Lote getLoteByIdWithDevedores(Lote lote, Integer lot_valido) throws IntranetException;
	
	public Boolean isLotePossuiDevedoresParaProcessar(Lote lote) throws IntranetException;
	
	public void removerLote(Lote lote) throws IntranetException;
	
	public void removerLoteComDependencias(Lote lote) throws IntranetException;
}