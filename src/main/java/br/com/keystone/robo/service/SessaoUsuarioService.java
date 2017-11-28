package br.com.keystone.robo.service;

import javax.inject.Inject;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.repository.IUsuarioRepository;
import br.com.keystone.robo.session.SessaoUsuario;
import br.com.keystone.robo.vo.MenuPrincipalVO;

public class SessaoUsuarioService implements ISessaoUsuarioService {
	
	@Inject
	private SessaoUsuario sessaoUsuario;
	
	@Inject
	private IUsuarioRepository usuarioRepository;
	
	@Inject
	private IMenuService menuService;
	
	@Override
	public void montarSessaoUsuario(Usuario usuario) throws IntranetException {
		
		sessaoUsuario.setUsu_cod(usuario.getUsu_cod());
		sessaoUsuario.setUsu_nome(usuario.getUsu_nome());
		sessaoUsuario.setUsu_usuario(usuario.getUsu_usuario());
		sessaoUsuario.setPef_nome(usuario.getPerfil().getPef_nome());
		sessaoUsuario.setIsAdmin(usuario.getPerfil().getPef_admin());
		sessaoUsuario.setPermissaoFunCodUsuario(usuarioRepository.carregarPermissoesByUsuario(usuario));
		
		MenuPrincipalVO menuPrincipalVO = menuService.criarEstruturaDadosMenuByUsuario(usuario);
		
		menuService.criarMenuHTML(menuPrincipalVO, menuPrincipalVO.getIdPai());
		
		sessaoUsuario.setHtmlMenu(menuPrincipalVO.getHtmlResult().toString());
		sessaoUsuario.setIsLogged(Boolean.TRUE);
	}
}