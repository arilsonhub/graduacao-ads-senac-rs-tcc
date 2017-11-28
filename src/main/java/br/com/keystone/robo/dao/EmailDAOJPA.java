package br.com.keystone.robo.dao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.Email;
import br.com.keystone.robo.repository.IEmailRepository;

public class EmailDAOJPA extends GenericDAO<Long, Email> implements IEmailRepository {

	@Inject
	public EmailDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());
	}
	
	@Override
	public List<Email> getAll() throws IntranetException {

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("select e from Email e where e.email_valido = 1");
			
			TypedQuery<Email> query = super.getEntityManager().createQuery(sql.toString(), Email.class);			
			List<Email> result =  query.getResultList();
			
			if(result == null || result.size() == 0) throw new IntranetException("Nenhum e-mail foi localizado na base de dados");
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntranetException("Ocorreu uma falha ao buscar os e-mails do banco de dados: " + e.getMessage());
		}
	}
}