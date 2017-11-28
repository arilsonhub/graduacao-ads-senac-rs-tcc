package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuracao implements Serializable {
	
	private static final long serialVersionUID = 8538550331950995337L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long con_cod;
	
	private String con_valor;
	
	private String con_descricao;
	
	private Integer con_valido;

	public Long getCon_cod() {
		return con_cod;
	}

	public void setCon_cod(Long con_cod) {
		this.con_cod = con_cod;
	}

	public String getCon_valor() {
		return con_valor;
	}

	public void setCon_valor(String con_valor) {
		this.con_valor = con_valor;
	}

	public String getCon_descricao() {
		return con_descricao;
	}

	public void setCon_descricao(String con_descricao) {
		this.con_descricao = con_descricao;
	}

	public Integer getCon_valido() {
		return con_valido;
	}

	public void setCon_valido(Integer con_valido) {
		this.con_valido = con_valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((con_cod == null) ? 0 : con_cod.hashCode());
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
		Configuracao other = (Configuracao) obj;
		if (con_cod == null) {
			if (other.con_cod != null)
				return false;
		} else if (!con_cod.equals(other.con_cod))
			return false;
		return true;
	}
}