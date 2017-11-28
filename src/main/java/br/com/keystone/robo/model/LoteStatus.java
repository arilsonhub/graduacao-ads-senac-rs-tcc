package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoteStatus implements Serializable {
	
	private static final long serialVersionUID = -7731011639557509588L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lts_cod;
	
	private String lts_descricao;
	
	private Integer lts_valido;

	public Long getLts_cod() {
		return lts_cod;
	}

	public void setLts_cod(Long lts_cod) {
		this.lts_cod = lts_cod;
	}

	public String getLts_descricao() {
		return lts_descricao;
	}

	public void setLts_descricao(String lts_descricao) {
		this.lts_descricao = lts_descricao;
	}

	public Integer getLts_valido() {
		return lts_valido;
	}

	public void setLts_valido(Integer lts_valido) {
		this.lts_valido = lts_valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lts_cod == null) ? 0 : lts_cod.hashCode());
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
		LoteStatus other = (LoteStatus) obj;
		if (lts_cod == null) {
			if (other.lts_cod != null)
				return false;
		} else if (!lts_cod.equals(other.lts_cod))
			return false;
		return true;
	}
}