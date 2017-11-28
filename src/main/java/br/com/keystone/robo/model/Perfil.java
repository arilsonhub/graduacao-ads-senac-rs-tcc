package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements Serializable {
	
	private static final long serialVersionUID = -563380639758306344L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pef_cod;
	
	private String pef_nome;
	
	private Integer pef_valido;
	
	private Integer pef_admin;
	
	public Long getPef_cod() {
		return pef_cod;
	}

	public void setPef_cod(Long pef_cod) {
		this.pef_cod = pef_cod;
	}

	public String getPef_nome() {
		return pef_nome;
	}

	public void setPef_nome(String pef_nome) {
		this.pef_nome = pef_nome;
	}

	public Integer getPef_valido() {
		return pef_valido;
	}

	public void setPef_valido(Integer pef_valido) {
		this.pef_valido = pef_valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pef_cod == null) ? 0 : pef_cod.hashCode());
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
		Perfil other = (Perfil) obj;
		if (pef_cod == null) {
			if (other.pef_cod != null)
				return false;
		} else if (!pef_cod.equals(other.pef_cod))
			return false;
		return true;
	}

	public Integer getPef_admin() {
		return pef_admin;
	}

	public void setPef_admin(Integer pef_admin) {
		this.pef_admin = pef_admin;
	}
}