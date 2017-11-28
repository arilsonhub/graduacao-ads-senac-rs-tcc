package br.com.keystone.robo.service;

import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.MenuEnum;
import br.com.keystone.robo.model.Menu;
import br.com.keystone.robo.model.MenuCategoria;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.repository.IMenuRepository;
import br.com.keystone.robo.vo.MenuPrincipalVO;

public class MenuPrincipalService implements IMenuService {
	
	@Inject
	private IMenuRepository menuRepository;
	
	@Inject
	private ServletContext servletContext;
	
	private Boolean isMenuStart = Boolean.TRUE;
	
	@Override
	public void criarMenuHTML(MenuPrincipalVO menuPrincipalVO, Long idPai) {
		
		Boolean isTagULOpen = Boolean.TRUE;
		
		for(Entry<Long, Menu> entry : menuPrincipalVO.getMapaMenu().get(idPai).entrySet()) {
			
			Menu menu = entry.getValue();
			
			if(menuPrincipalVO.getConjuntoMenusUsuario().contains(menu.getMem_cod()) || menuPrincipalVO.getPerfilUsuario().getPef_admin().equals(1)){
			
				menuPrincipalVO.getHtmlResult().append("<ul ");
				isTagULOpen = Boolean.TRUE;
				
				if(isMenuStart){
					onMenuStart(menuPrincipalVO.getHtmlResult());	
				}else{
					menuPrincipalVO.getHtmlResult().append(">");
				}
				
				menuPrincipalVO.getHtmlResult().append("<li><a href=\"").append(servletContext.getContextPath().concat(menu.getMem_action()))
				.append("\"><i class='").append(menu.getMem_img()).append("'></i>").append(menu.getMem_descricao()).append("</a>");
				
				if(menuPrincipalVO.getMapaMenu().containsKey(menu.getMem_cod())){					
					this.criarMenuHTML(menuPrincipalVO, menu.getMem_cod());
				}
				
				menuPrincipalVO.getHtmlResult().append("</li>");
				
				menuPrincipalVO.getHtmlResult().append("</ul>");
				isTagULOpen = Boolean.FALSE;
				
			}else{
				
				if(menuPrincipalVO.getMapaMenu().containsKey(menu.getMem_cod()) && verificarFilhoMenuPermissao(menuPrincipalVO, menu.getMem_cod())){

					if(isTagULOpen && isMenuStart){
						menuPrincipalVO.getHtmlResult().append("<ul ");
						onMenuStart(menuPrincipalVO.getHtmlResult());
					}
					else if(isTagULOpen){
						menuPrincipalVO.getHtmlResult().append("<ul>");
					} 
					
					menuPrincipalVO.getHtmlResult().append("<li><a href=\"").append(servletContext.getContextPath().concat(menu.getMem_action())).append("\"><i class='fa fa-edit fa-3x'></i>").append(menu.getMem_descricao()).append("</a>");
					this.criarMenuHTML(menuPrincipalVO, menu.getMem_cod());
					menuPrincipalVO.getHtmlResult().append("</li>");
					
					if(isTagULOpen){
						menuPrincipalVO.getHtmlResult().append("</ul>");
						isTagULOpen = Boolean.FALSE;
					}
				}
			}
		}		
	}
	
	private void onMenuStart(StringBuilder htmlResult){
		htmlResult.append("class='nav' id='main-menu'>");
		htmlResult.append("<li class='text-center'>");
		htmlResult.append("<img src='assets/img/find_user.png' class='user-image img-responsive'/>");
		htmlResult.append("</li>");
		isMenuStart = Boolean.FALSE;
	}
	
	@Override
	public Boolean verificarFilhoMenuPermissao(MenuPrincipalVO menuPrincipalVO, Long idPai) {
		Integer filhosComPermissao = 0;
		for(Entry<Long, Menu> entry : menuPrincipalVO.getMapaMenu().get(idPai).entrySet()){
				if(menuPrincipalVO.getConjuntoMenusUsuario().contains(entry.getValue().getMem_cod())){
						filhosComPermissao++;
				}else{					
						if(menuPrincipalVO.getMapaMenu().containsKey(entry.getValue().getMem_cod())) {
								if(verificarFilhoMenuPermissao(menuPrincipalVO, entry.getValue().getMem_cod())){
									filhosComPermissao++;
								}
						}
				}
		}
		if(filhosComPermissao > 0) return true;
		return false;
	}
	
	public MenuPrincipalVO criarEstruturaDadosMenuByUsuario(Usuario usuario) throws IntranetException {		
		
		MenuPrincipalVO menuPrincipalVO = new MenuPrincipalVO();
		MenuCategoria menuCategoria = new MenuCategoria();
		StringBuilder htmlResult = new StringBuilder();
		
		menuCategoria.setMct_cod(MenuEnum.MenuPrincipal.getMenuChave());
		
		menuPrincipalVO.setMapaMenu(menuRepository.carregarMenuByCategoria(menuCategoria));
		menuPrincipalVO.setConjuntoMenusUsuario(menuRepository.carregarMenuCodByUsuario(usuario));
		menuPrincipalVO.setPerfilUsuario(usuario.getPerfil());
		menuPrincipalVO.setHtmlResult(htmlResult);
		menuPrincipalVO.setIdPai(0L);
		
		return menuPrincipalVO;
	}
}