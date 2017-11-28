package br.com.keystone.robo.service;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Usuario;

public interface ISessaoUsuarioService {

	public void montarSessaoUsuario(Usuario usuario) throws IntranetException;
}