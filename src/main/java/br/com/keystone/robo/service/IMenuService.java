package br.com.keystone.robo.service;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.vo.MenuPrincipalVO;

public interface IMenuService {

	public void criarMenuHTML(MenuPrincipalVO menuPrincipalVO, Long idPai);
	
	public MenuPrincipalVO criarEstruturaDadosMenuByUsuario(Usuario usuario) throws IntranetException;
	
	public Boolean verificarFilhoMenuPermissao(MenuPrincipalVO menuPrincipalVO, Long idPai);
}