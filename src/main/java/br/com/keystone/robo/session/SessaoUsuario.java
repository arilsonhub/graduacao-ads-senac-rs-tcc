package br.com.keystone.robo.session;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("sessaoUsuario")
@SessionScoped
public class SessaoUsuario implements Serializable {
	
	private static final long serialVersionUID = 7121172481877919828L;

	private Long usu_cod;
	
	private String usu_nome;
	
	private String usu_usuario;
	
	private String pef_nome;
	
	private Integer isAdmin;
	
	private String htmlMenu;
	
	private Boolean isLogged;
	
	private Set<Long> permissaoFunCodUsuario;
	
	public SessaoUsuario(){
		isLogged = Boolean.FALSE;
	}
	
	public String getUsu_nome() {
		return usu_nome;
	}

	public void setUsu_nome(String usu_nome) {
		this.usu_nome = usu_nome;
	}

	public String getUsu_usuario() {
		return usu_usuario;
	}

	public void setUsu_usuario(String usu_usuario) {
		this.usu_usuario = usu_usuario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usu_usuario == null) ? 0 : usu_usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessaoUsuario other = (SessaoUsuario) obj;
		if (usu_usuario == null) {
			if (other.usu_usuario != null)
				return false;
		} else if (!usu_usuario.equals(other.usu_usuario))
			return false;
		return true;
	}

	public Long getUsu_cod() {
		return usu_cod;
	}

	public void setUsu_cod(Long usu_cod) {
		this.usu_cod = usu_cod;
	}

	public String getHtmlMenu() {
		return htmlMenu;
	}

	public void setHtmlMenu(String htmlMenu) {
		this.htmlMenu = htmlMenu;
	}

	public Set<Long> getPermissaoFunCodUsuario() {
		return permissaoFunCodUsuario;
	}

	public void setPermissaoFunCodUsuario(Set<Long> permissaoFunCodUsuario) {
		this.permissaoFunCodUsuario = permissaoFunCodUsuario;
	}

	public String getPef_nome() {
		return pef_nome;
	}

	public void setPef_nome(String pef_nome) {
		this.pef_nome = pef_nome;
	}

	public Boolean isLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
}