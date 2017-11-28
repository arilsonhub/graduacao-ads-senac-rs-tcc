package br.com.keystone.robo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.ControleLoteErro;
import br.com.keystone.robo.repository.IControleLoteErroRepository;

public class ControleLoteErroDAOJPA extends GenericDAO<Long, ControleLoteErro> implements IControleLoteErroRepository {
	
	@Inject
	public ControleLoteErroDAOJPA(EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory.createEntityManager());		
	}

	@Override
	public void inserirErro(ControleLoteErro controleLoteErro) {
		try{
			super.getEntityManager().getTransaction().begin();
			super.save(controleLoteErro);
			super.getEntityManager().getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}