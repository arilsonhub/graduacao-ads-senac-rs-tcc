package br.com.keystone.robo.vo;

import java.util.HashMap;
import java.util.Set;

import br.com.keystone.robo.model.Menu;
import br.com.keystone.robo.model.Perfil;

public class MenuPrincipalVO {

	private Set<Long> conjuntoMenusUsuario;
	
	private Perfil perfilUsuario;
	
	private HashMap<Long, HashMap<Long, Menu>> mapaMenu;
	
	private Long idPai;
	
	private StringBuilder htmlResult;

	public Set<Long> getConjuntoMenusUsuario() {
		return conjuntoMenusUsuario;
	}

	public void setConjuntoMenusUsuario(Set<Long> conjuntoMenusUsuario) {
		this.conjuntoMenusUsuario = conjuntoMenusUsuario;
	}

	public HashMap<Long, HashMap<Long, Menu>> getMapaMenu() {
		return mapaMenu;
	}

	public void setMapaMenu(HashMap<Long, HashMap<Long, Menu>> mapaMenu) {
		this.mapaMenu = mapaMenu;
	}

	public Long getIdPai() {
		return idPai;
	}

	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}

	public StringBuilder getHtmlResult() {
		return htmlResult;
	}

	public void setHtmlResult(StringBuilder htmlResult) {
		this.htmlResult = htmlResult;
	}

	public Perfil getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(Perfil perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}	
}