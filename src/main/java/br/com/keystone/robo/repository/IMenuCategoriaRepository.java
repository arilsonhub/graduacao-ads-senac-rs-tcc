package br.com.keystone.robo.repository;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.MenuCategoria;

public interface IMenuCategoriaRepository {

	public MenuCategoria carregarMenuCategoria(MenuCategoria menuCategoria) throws IntranetException;
}