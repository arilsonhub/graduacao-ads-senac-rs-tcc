package br.com.keystone.robo.dao;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.Configuracao;
import br.com.keystone.robo.repository.IConfiguracaoRepository;

public class ConfiguracaoDAOJPA extends GenericDAO<Long, Configuracao> implements IConfiguracaoRepository {

	@Inject
	public ConfiguracaoDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());		
	}

	@Override
	public Configuracao getConfiguracaoById(Configuracao configuracao) throws IntranetException {
		
		try{
		
			StringBuilder sql = new StringBuilder();
			sql.append("select c from Configuracao c where c.con_cod = :con_cod and c.con_valido = 1");
			
			TypedQuery<Configuracao> query = super.getEntityManager().createQuery(sql.toString(), Configuracao.class).setMaxResults(1);
			query.setParameter("con_cod", configuracao.getCon_cod());
			
			return query.getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Ocorreu uma falha ao buscar uma configuração do banco de dados");
		}
	}
	
	public HashMap<Long,Configuracao> getConfiguracoesByIds(List<Long> ids) throws IntranetException {
		
		try{			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select c from Configuracao c where c.con_cod in (:con_cod) and c.con_valido = 1");			
			TypedQuery<Configuracao> query = super.getEntityManager().createQuery(sql.toString(), Configuracao.class);
			query.setParameter("con_cod", ids);
			List<Configuracao> result = query.getResultList();
			
			HashMap<Long,Configuracao> mapResult = new HashMap<Long, Configuracao>();
			
			for(Configuracao config : result){
				mapResult.put(config.getCon_cod(), config);
			}
			
			return mapResult;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new IntranetException("Ocorreu uma falha ao buscar as configurações do banco de dados");
		}
	}
}