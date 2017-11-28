package br.com.keystone.robo.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.keystone.robo.annotations.AdevedorDAOJPA;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.helper.DateHelper;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.repository.IDevedorRepository;
import br.com.keystone.robo.vo.CampanhaVO;

@AdevedorDAOJPA
public class DevedorDAOJPA extends GenericDAO<Long, Devedor> implements IDevedorRepository {

	@Inject
	public DevedorDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());		
	}

	@Override
	public List<Devedor> buscarDevedoresByCampanha(CampanhaVO campanhaVO) throws IntranetException {
		// Sem implementação para esta classe
		return null;
	}
	
	@Override
	public void atualizarDevedor(Devedor devedor) {
		super.getEntityManager().getTransaction().begin();
		super.update(devedor);		
		super.getEntityManager().getTransaction().commit();
	}

	@Override
	public Devedor getDevedorByCpfCnpj(Devedor devedor) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select dev ");
			query.append(" from Devedor dev ");			
			query.append(" where dev.dev_cpf_cnpj = :dev_cpf_cnpj");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("dev_cpf_cnpj", devedor.getDev_cpf_cnpj());
			queryObject.setMaxResults(1);
			return (Devedor) queryObject.getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
		catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar o devedor na base de dados");
		}
	}
	
	@Override
	public Devedor getDevedorById(Devedor devedor) throws IntranetException {
		
		try{
			StringBuilder query = new StringBuilder();
			query.append(" select dev ");
			query.append(" from Devedor dev ");
			query.append(" join fetch dev.dividas ");
			query.append(" where dev.dev_cod = :dev_cod");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());			
			queryObject.setParameter("dev_cod", devedor.getDev_cod());
			queryObject.setMaxResults(1);
			return (Devedor) queryObject.getSingleResult();
			
		}catch(Exception e){
			throw new IntranetException("Houve uma falha ao tentar localizar o devedor na base de dados");
		}
	}
	
	@Override
	public Boolean isDeveImpedirReprocessamentoDeDevedor(Devedor devedor) {		
		try{
			
			Integer mesAtual = DateHelper.getDateMonth(new Date());
			Integer mesDataProcessamentoDevedor = DateHelper.getDateMonth(devedor.getDev_datainclusao());
			
			return (mesAtual > mesDataProcessamentoDevedor ? Boolean.FALSE : Boolean.TRUE);
			
		}catch(Exception e){
			return Boolean.FALSE;
		}
	}
}