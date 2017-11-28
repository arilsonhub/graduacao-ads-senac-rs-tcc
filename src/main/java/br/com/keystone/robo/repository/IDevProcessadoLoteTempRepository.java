package br.com.keystone.robo.repository;

import java.util.List;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.DevProcessadoLoteTemp;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.vo.DevProcessadoLoteTempVO;
import br.com.keystone.robo.vo.PaginatorVO;

public interface IDevProcessadoLoteTempRepository {
	
	public void marcarProcessamentoDevedor(Devedor devedor, Integer dlt_valido, Lote lote) throws IntranetException;
	
	public void removerDevedoresDoLoteComMarcacao(Integer dlt_valido, Lote lote) throws IntranetException;
	
	public void colocarDevedoresNoLote(Lote lote) throws IntranetException;
	
	public PaginatorVO<DevProcessadoLoteTempVO> getDevedoresJaProcessadosByLote(Lote lote, Integer page) throws IntranetException;
	
	public DevProcessadoLoteTemp getDevTempByDevCodAndLoteCod(Devedor devedor, Lote lote) throws IntranetException;
	
	public List<DevProcessadoLoteTemp> getDevedoresByLote(Lote lote) throws IntranetException;
	
	public void marcarTodosDevedoresJaProcessadosDoLote(Lote lote) throws IntranetException;
	
	public void removerDevedoresDaTabelaTemporaria(Lote lote) throws IntranetException;
	
	public void removerDevedoresDaTabelaTemporariaComCommit(Lote lote) throws IntranetException;
	
	public Boolean isDevedoresExistentesNoLoteComMarcacao(Integer dlt_valido, Lote lote) throws IntranetException;
}