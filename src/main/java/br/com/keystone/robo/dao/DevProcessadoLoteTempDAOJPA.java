package br.com.keystone.robo.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.DevProcessadoLoteTemp;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.repository.IDevProcessadoLoteTempRepository;
import br.com.keystone.robo.vo.DevProcessadoLoteTempVO;
import br.com.keystone.robo.vo.PaginatorVO;

public class DevProcessadoLoteTempDAOJPA extends GenericDAO<Long, DevProcessadoLoteTemp> implements IDevProcessadoLoteTempRepository {
	
	@Inject
	public DevProcessadoLoteTempDAOJPA(EntityManager entityManager){
		super(entityManager);
	}
	
	@Override
	public void marcarProcessamentoDevedor(Devedor devedor, Integer dlt_valido, Lote lote) throws IntranetException {
		try{
			DevProcessadoLoteTemp temp = getDevTempByDevCodAndLoteCod(devedor,lote);
			temp.setDlt_valido(dlt_valido);
			super.getEntityManager().merge(temp);
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível selecionar o devedor");
		}
	}
	
	public Boolean isDevedoresExistentesNoLoteComMarcacao(Integer dlt_valido, Lote lote) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select count(t) from DevProcessadoLoteTemp t where t.dlt_valido = :dlt_valido ");
			query.append(" and t.lote.lot_cod = :lot_cod");
			Query queryObject = super.getEntityManager().createQuery(query.toString());
			queryObject.setParameter("dlt_valido", dlt_valido);
			queryObject.setParameter("lot_cod", lote.getLot_cod());			
			Long totalRecords = (Long)queryObject.getResultList().get(0);
			
			return (totalRecords > 0);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível verificar os devedores existentes com a marcação solicitada");
		}
	}

	@Override
	public void removerDevedoresDoLoteComMarcacao(Integer dlt_valido, Lote lote) throws IntranetException {
		
		try{
			
			if(isDevedoresExistentesNoLoteComMarcacao(dlt_valido, lote)){
			
				StringBuilder query = new StringBuilder();
				query.append(" DELETE FROM DevProcessadoLoteTemp t WHERE t.dlt_valido = :dlt_valido ");
				query.append("and t.lote.lot_cod = :lot_cod");
				Query queryObject = super.getEntityManager().createQuery(query.toString());
				queryObject.setParameter("dlt_valido", dlt_valido);
				queryObject.setParameter("lot_cod", lote.getLot_cod());
				queryObject.executeUpdate();
				
			}else{
				
				if(dlt_valido.equals(1)){
					throw new IntranetException("Você deve selecionar algum devedor para remover do lote");
				}else{
					throw new IntranetException("Não há devedores desmarcados para remover");
				}				
			}
			
		}catch(IntranetException ie){
			throw new IntranetException(ie.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível remover os devedores do lote com a marcação solicitada");
		}
	}
	
	@Override
	public void removerDevedoresDaTabelaTemporaria(Lote lote) throws IntranetException {
		
		try{			
			StringBuilder query = new StringBuilder();
			query.append(" DELETE FROM DevProcessadoLoteTemp t WHERE t.lote.lot_cod = :lot_cod ");			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.executeUpdate();
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível remover os devedores do lote");
		}
	}
	
	@Override
	public void removerDevedoresDaTabelaTemporariaComCommit(Lote lote) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" DELETE FROM DevProcessadoLoteTemp t WHERE t.lote.lot_cod = :lot_cod ");			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.executeUpdate();
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível remover os devedores do lote");
		}
	}

	@Override
	public void colocarDevedoresNoLote(Lote lote) throws IntranetException {
		
		List<DevProcessadoLoteTemp> listaDevedores = getDevedoresByLote(lote);
		
		for(DevProcessadoLoteTemp temp : listaDevedores){
			lote.getDevedores().add(temp.getDevedor());
		}
	}
	
	@Override
	public DevProcessadoLoteTemp getDevTempByDevCodAndLoteCod(Devedor devedor, Lote lote) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select devT ");
			query.append(" from DevProcessadoLoteTemp devT ");
			query.append(" join fetch devT.devedor dev ");
			query.append(" where devT.devedor.dev_cod = :dev_cod and devT.lote.lot_cod = :lot_cod");
			
			TypedQuery<DevProcessadoLoteTemp> queryObject = super.getEntityManager().createQuery(query.toString(),DevProcessadoLoteTemp.class);			
			queryObject.setParameter("dev_cod", devedor.getDev_cod());
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.setMaxResults(1);
			
			return queryObject.getSingleResult();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar buscar o devedor");
		}		
	}
	
	@Override
	public List<DevProcessadoLoteTemp> getDevedoresByLote(Lote lote) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select devT ");
			query.append(" from DevProcessadoLoteTemp devT ");
			query.append(" join fetch devT.devedor dev ");
			query.append(" where devT.lote.lot_cod = :lot_cod ");
			
			TypedQuery<DevProcessadoLoteTemp> queryObject = super.getEntityManager().createQuery(query.toString(),DevProcessadoLoteTemp.class);			
			queryObject.setParameter("lot_cod", lote.getLot_cod());			
			
			return queryObject.getResultList();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar buscar os devedores");
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatorVO<DevProcessadoLoteTempVO> getDevedoresJaProcessadosByLote(Lote lote, Integer page) throws IntranetException {		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select count(devT) from DevProcessadoLoteTemp devT ");
			query.append(" where devT.lote.lot_cod = :lot_cod ");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			Long totalRecords = (Long)queryObject.getResultList().get(0);
			
			if(totalRecords > 0){
				
				query = new StringBuilder();
				query.append(" select devT ");
				query.append(" from DevProcessadoLoteTemp devT ");
				query.append(" join fetch devT.devedor dev ");
				query.append(" where devT.lote.lot_cod = :lot_cod");
				
				queryObject = super.getEntityManager().createQuery(query.toString());
				queryObject.setParameter("lot_cod", lote.getLot_cod());
				
				Integer recordsPerPage = 100;
				Integer pages          = new Double(Math.ceil(new BigDecimal(totalRecords).divide(new BigDecimal(recordsPerPage)).setScale(2, RoundingMode.HALF_UP).doubleValue())).intValue();				
				page                   = (page == null ? 1 : (recordsPerPage * page) - recordsPerPage);
						
				queryObject.setFirstResult(page);
				queryObject.setMaxResults(recordsPerPage);
				List<DevProcessadoLoteTemp> result = queryObject.getResultList();
				List<DevProcessadoLoteTempVO> listaDevedores = new ArrayList<DevProcessadoLoteTempVO>();
				
				PaginatorVO<DevProcessadoLoteTempVO> paginator = new PaginatorVO<DevProcessadoLoteTempVO>();
				paginator.setPage(page);
				paginator.setPages(pages);
				
				for(DevProcessadoLoteTemp temp : result){
					DevProcessadoLoteTempVO vo = new DevProcessadoLoteTempVO();
					vo.setDev_cod(temp.getDevedor().getDev_cod());
					vo.setDev_cpf_cnpj(temp.getDevedor().getDev_cpf_cnpj());
					vo.setLot_cod(temp.getLote().getLot_cod());
					listaDevedores.add(vo);
				}
				
				paginator.setLista(listaDevedores);
				return paginator;
			
			}else{
				throw new IntranetException("Nenhum devedor já processado localizado");
			}
			
		}catch(IntranetException ie){
			throw new IntranetException(ie.getMessage());
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar buscar os devedores já processados");
		}
	}
	
	@Override
	public void marcarTodosDevedoresJaProcessadosDoLote(Lote lote) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" UPDATE DevProcessadoLoteTemp t SET t.dlt_valido = 1 WHERE t.lote.lot_cod = :lot_cod ");			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível marcar todos os devedores do lote");
		}
	}
}