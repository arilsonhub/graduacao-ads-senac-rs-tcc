package br.com.keystone.robo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.LoteStatus;
import br.com.keystone.robo.repository.ILoteStatusRepository;

public class LoteStatusDAOJPA extends GenericDAO<Long, LoteStatus> implements ILoteStatusRepository {

	@Inject
	public LoteStatusDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());		
	}

	@Override
	public LoteStatus getLoteStatusById(LoteStatus loteStatus) throws IntranetException {		
		
		try{
			
			StringBuilder sql = new StringBuilder();
			sql.append("select lts from LoteStatus lts where lts.lts_cod = :lts_cod and lts.lts_valido = 1");
			
			TypedQuery<LoteStatus> query = super.getEntityManager().createQuery(sql.toString(), LoteStatus.class).setMaxResults(1);
			query.setParameter("lts_cod", loteStatus.getLts_cod());
			
			return query.getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Ocorreu uma falha ao buscar o status do lote do banco de dados");
		}
	}
}