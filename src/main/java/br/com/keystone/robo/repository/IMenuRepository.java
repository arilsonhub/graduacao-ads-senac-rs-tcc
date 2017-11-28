package br.com.keystone.robo.repository;

import java.util.HashMap;
import java.util.Set;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Menu;
import br.com.keystone.robo.model.MenuCategoria;
import br.com.keystone.robo.model.Usuario;

public interface IMenuRepository {

	public HashMap<Long, HashMap<Long, Menu>> carregarMenuByCategoria(MenuCategoria menuCategoria) throws IntranetException;
	
	public Set<Long> carregarMenuCodByUsuario(Usuario usuario);
}