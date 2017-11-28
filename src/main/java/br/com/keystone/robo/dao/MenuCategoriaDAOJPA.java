package br.com.keystone.robo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.MenuCategoria;
import br.com.keystone.robo.repository.IMenuCategoriaRepository;

public class MenuCategoriaDAOJPA extends GenericDAO<Long, MenuCategoria> implements IMenuCategoriaRepository {

	@Inject
	public MenuCategoriaDAOJPA(EntityManager entityManager) {
		super(entityManager);		
	}

	@Override
	public MenuCategoria carregarMenuCategoria(MenuCategoria menuCategoria) throws IntranetException {
		
		try{			
			return super.getById(menuCategoria.getMct_cod());			
		}catch(Exception e){
			throw new IntranetException("Ocorreu uma falha ao criar a sessão, tente novamente");
		}
	}
}