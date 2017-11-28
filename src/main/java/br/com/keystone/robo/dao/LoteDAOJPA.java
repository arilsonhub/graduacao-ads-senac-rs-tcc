package br.com.keystone.robo.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import br.com.keystone.robo.annotations.AdevedorDAOJPA;
import br.com.keystone.robo.business.exceptions.DevedorJaFoiProcessadoException;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.helper.DateHelper;
import br.com.keystone.robo.model.ControleLoteErro;
import br.com.keystone.robo.model.DevProcessadoLoteTemp;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Divida;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.model.LoteStatus;
import br.com.keystone.robo.model.Parcela;
import br.com.keystone.robo.repository.IDevedorRepository;
import br.com.keystone.robo.repository.ILoteRepository;
import br.com.keystone.robo.repository.ILoteStatusRepository;
import br.com.keystone.robo.vo.ControleLoteErroVO;
import br.com.keystone.robo.vo.DadosLoteVO;
import br.com.keystone.robo.vo.PaginatorVO;

public class LoteDAOJPA extends GenericDAO<Long, Lote> implements ILoteRepository {
	
	@Inject
	private ILoteStatusRepository loteStatusRepository;
	
	@Inject @AdevedorDAOJPA
	private IDevedorRepository devedorRepository;
	
	@Inject
	public LoteDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());		
	}

	@Override
	public void gravarLote(Lote lote, List<Devedor> listaDevedores) throws IntranetException, DevedorJaFoiProcessadoException {
		
		try{
			  List<Devedor> devedoresLote = new ArrayList<Devedor>();
			  Boolean existemDevedoresJaProcessados = Boolean.FALSE;
			  super.getEntityManager().getTransaction().begin();
			  super.getEntityManager().persist(lote);
			  for (Devedor devedor : listaDevedores) {
				  Devedor devedorLocalizado = devedorRepository.getDevedorByCpfCnpj(devedor);
				  if(devedorLocalizado == null){
					  super.getEntityManager().persist(devedor);
					  devedoresLote.add(devedor);					  				  
					  this.gravarDividasDevedor(devedor.getDividas(), devedor);
				  }else{
					  if(devedorRepository.isDeveImpedirReprocessamentoDeDevedor(devedorLocalizado)){
						  inserirDevedorNaTabelaTemporaria(devedorLocalizado,lote);
						  existemDevedoresJaProcessados = Boolean.TRUE;
					  }
				  }
			  }
			  
	    	  if(existemDevedoresJaProcessados){
	    		  lote.setLot_valido(0);
	    		  super.getEntityManager().merge(lote);
	    		  super.getEntityManager().getTransaction().commit();	    		  
	    		  throw new DevedorJaFoiProcessadoException("Existem devedores já processados anteriormente",lote);
	    	  }else{
	    		  lote.setDevedores(devedoresLote);
	    		  super.getEntityManager().merge(lote);	    		  
	    		  super.getEntityManager().getTransaction().commit();
	    	  }
	    	  
		}catch(DevedorJaFoiProcessadoException djfpe){
			throw new DevedorJaFoiProcessadoException(djfpe.getMessage(),djfpe.getLote());
		}catch(Exception e){
			e.printStackTrace();
			super.getEntityManager().getTransaction().rollback();
			throw new IntranetException("Houve uma falha ao tentar gravar o lote na base de dados");
		}finally{
			super.getEntityManager().clear();
	    	super.getEntityManager().close();
		}
	}
	
	private void inserirDevedorNaTabelaTemporaria(Devedor devedor, Lote lote){
		
		DevProcessadoLoteTemp temp = new DevProcessadoLoteTemp();
		temp.setDevedor(devedor);
		temp.setDlt_valido(0);
		temp.setLote(lote);
		super.getEntityManager().persist(temp);
	}
	
	private void gravarDividasDevedor(List<Divida> dividas, Devedor devedor){
		  for (Divida divida : dividas) {
			  divida.setDevedor(devedor);
			  super.getEntityManager().persist(divida);			  
			  this.gravarParcelasDivida(divida.getParcelas(), divida);
		  }
	}
	
	private void gravarParcelasDivida(List<Parcela> parcelas, Divida divida){
		  for (Parcela parcela : parcelas) {
			  parcela.setDivida(divida);
		      super.getEntityManager().persist(parcela);
		  }
	}
	
	public void atualizarLote(Lote lote) throws IntranetException {
		try{
			super.getEntityManager().getTransaction().begin();
			super.update(lote);
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar atualizar o lote");
		}
	}

	@Override
	public Boolean isLoteEmStatus(Lote lote, Long lts_cod) throws IntranetException {		
		try{		
			StringBuilder query = new StringBuilder();
			query.append(" select lot ");
			query.append(" from Lote lot ");
			query.append(" join fetch lot.loteStatus lts ");
			query.append(" where lot.loteStatus.lts_cod = :lts_cod and lot.lot_cod = :lot_cod and lot.lot_valido = 1");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());
			queryObject.setParameter("lts_cod", lts_cod);
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.setMaxResults(1);
			Lote loteLocalizado = (Lote) queryObject.getSingleResult();
			
			if(lts_cod.equals(loteLocalizado.getLoteStatus().getLts_cod())) return Boolean.TRUE;
			
		}catch(NoResultException nre){
			return Boolean.FALSE;
		}
		catch(Exception e){
			throw new IntranetException("Ocorreu uma falha ao acessar a base de dados");
		}		
		return Boolean.FALSE;
	}

	@Override
	public Lote getLoteById(Lote lote) throws IntranetException {		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select lot ");
			query.append(" from Lote lot ");
			query.append(" join fetch lot.loteStatus lts ");
			query.append(" where lot.lot_cod = :lot_cod and lot.lot_valido = 1");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.setMaxResults(1);
			return (Lote) queryObject.getSingleResult();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar o lote na base de dados");
		}
	}
	
	@Override
	public Lote getLoteByIdWithDevedores(Lote lote, Integer lot_valido) throws IntranetException {		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select lot ");
			query.append(" from Lote lot ");
			query.append(" left join fetch lot.devedores dev ");			
			query.append(" where lot.lot_cod = :lot_cod and lot.lot_valido = :lot_valido ");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.setParameter("lot_valido", lot_valido);
			return (Lote) queryObject.getSingleResult();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar o lote na base de dados");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PaginatorVO<ControleLoteErroVO> getLoteByIdWithErrors(Lote lote, Integer page) throws IntranetException {	
		try{
			Query queryObject = super.getEntityManager().createQuery("select count(clt) from ControleLoteErro clt where clt.lote.lot_cod = :lot_cod ");
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			Long totalRecords = (Long)queryObject.getResultList().get(0);
			
			if(totalRecords > 0){
				
				StringBuilder query = new StringBuilder();			
				query.append(" from ControleLoteErro clt ");			
				query.append(" where clt.lote.lot_cod = :lot_cod ");
				
				queryObject = super.getEntityManager().createQuery(query.toString());
				queryObject.setParameter("lot_cod", lote.getLot_cod());
				
				Integer recordsPerPage = 100;
				Integer pages          = new Double(Math.ceil(new BigDecimal(totalRecords).divide(new BigDecimal(recordsPerPage)).setScale(2, RoundingMode.HALF_UP).doubleValue())).intValue();				
				page                   = (page == null ? 1 : (recordsPerPage * page) - recordsPerPage);
						
				queryObject.setFirstResult(page);
				queryObject.setMaxResults(recordsPerPage);
				List<ControleLoteErro> result = queryObject.getResultList();
				List<ControleLoteErroVO> listaErrosLote = new ArrayList<ControleLoteErroVO>();
				
				PaginatorVO<ControleLoteErroVO> paginator = new PaginatorVO<ControleLoteErroVO>();
				paginator.setPage(page);
				paginator.setPages(pages);
				
				for(ControleLoteErro erro : result){
					ControleLoteErroVO vo = new ControleLoteErroVO();
					vo.setClt_datahora(erro.getClt_datahora());
					vo.setClt_descricao(erro.getClt_descricao());
					vo.setClt_processo(erro.getClt_processo());
					listaErrosLote.add(vo);
				}
				
				paginator.setLista(listaErrosLote);
				return paginator;
			
			}else{
				throw new IntranetException("Nenhum erro localizado");
			}
			
		}catch(IntranetException ie){
			throw new IntranetException(ie.getMessage());
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar o lote na base de dados");
		}
	}
	
	@Override
	public Boolean isLotePossuiDevedoresParaProcessar(Lote lote) throws IntranetException {	
		try{
			
			if(lote.getDevedores() != null && lote.getDevedores().size() > 0) return Boolean.TRUE;
			
			return Boolean.FALSE;
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar contar a quantidade de devedores no lote");
		}
	}
	
	public Lote getLoteComDevedoresNaoProcessados(Lote lote) throws IntranetException {
		
		try{
			
			StringBuilder query = new StringBuilder();
			query.append(" select lot ");
			query.append(" from Lote lot ");
			query.append(" join fetch lot.loteStatus lts ");
			query.append(" join fetch lot.devedores dev ");
			query.append(" where lot.lot_cod = :lot_cod and lot.lot_valido = 1");
			query.append(" and dev.dev_processado = 0 ");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.setMaxResults(1);
			return (Lote) queryObject.getSingleResult();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar devedores não processados do lote na base de dados");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DadosLoteVO> getLotesByDataInclusao(Date lot_datainclusao) throws IntranetException {
		
		try{
			
			StringBuilder query = new StringBuilder();
			query.append(" select lot.lot_cod as idLote, ");
			query.append(" 		  lts.lts_descricao as status, ");
			query.append(" 		  lts.lts_cod as statusId, ");
			query.append(" 		  (select count(clt_cod) ");
			query.append(" 			  from controleloteerro ");
			query.append(" 				where clt_lot_cod = lot.lot_cod) as erros, ");
			query.append(" 		  (select count(dev.dev_id) ");
			query.append(" 			  from devedor dev, lotedevedor ldv ");
			query.append(" 			  where dev.dev_id = ldv.ldv_dev_id ");
			query.append(" 			  and ldv_lot_cod = lot_cod) as numeroContratos, ");
			query.append(" 		  (select count(dev.dev_id) ");
			query.append(" 			  from devedor dev, lotedevedor ldv ");
			query.append(" 			  where dev.dev_id = ldv.ldv_dev_id ");
			query.append(" 			  and ldv.ldv_lot_cod = lot.lot_cod ");
			query.append(" 			  and dev.dev_processado = 1) as numeroBoletosGerados ");
			query.append(" from lote lot ");			
			query.append(" inner join lotestatus lts on (lot.lot_lts_cod = lts.lts_cod) ");
			query.append(" where TO_CHAR(lot_datainclusao,'YYYY-MM-DD') = :lot_datainclusao and lot.lot_valido = 1 ");
			query.append(" order by lot.lot_cod desc ");
			
			Query queryObject =  super.getEntityManager().createNativeQuery(query.toString());
			queryObject.setParameter("lot_datainclusao", DateHelper.formatDateToISO(lot_datainclusao));
			List<Object[]> result = queryObject.getResultList();
			List<DadosLoteVO> listaLotes = new ArrayList<DadosLoteVO>();
			
			if(result != null){
				for(Object[] linha : result)					
					listaLotes.add(new DadosLoteVO(linha[0].toString(),linha[1].toString(),linha[2].toString(),linha[3].toString(),linha[4].toString(),linha[5].toString()));
			}
			
			return listaLotes;
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar buscar os lotes da base de dados");
		}
	}
	
	@Override
	public Lote alterarStatusLote(Lote lote,Long lts_cod) throws IntranetException {
		lote = this.getLoteById(lote);		
		LoteStatus loteStatus = new LoteStatus();
		loteStatus.setLts_cod(lts_cod);		
		loteStatus = loteStatusRepository.getLoteStatusById(loteStatus);		
		lote.setLoteStatus(loteStatus);		
		this.atualizarLote(lote);
		return lote;
	}
	
	@Override
	public void removerLote(Lote lote) throws IntranetException {
		
		try{
			super.getEntityManager().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" DELETE FROM Lote lot WHERE lot.lot_cod = :lot_cod ");			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("lot_cod", lote.getLot_cod());
			queryObject.executeUpdate();
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível remover o lote da base de dados");
		}
	}

	@Override
	public void removerLoteComDependencias(Lote lote) throws IntranetException {
		try{
			super.getEntityManager().getTransaction().begin();
			lote = getLoteByIdWithDevedores(lote, 0);
			super.getEntityManager().remove(lote);
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Não foi possível remover o lote da base de dados");
		}
	}
}