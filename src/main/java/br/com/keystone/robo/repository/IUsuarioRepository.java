package br.com.keystone.robo.repository;

import java.util.Set;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Usuario;

public interface IUsuarioRepository {

	public Usuario carregarUsuarioByLoginSenha(Usuario usuario) throws IntranetException;
	
	public Usuario carregarUsuarioById(Long usu_cod) throws IntranetException;
	
	public Set<Long> carregarPermissoesByUsuario(Usuario usuario);
}