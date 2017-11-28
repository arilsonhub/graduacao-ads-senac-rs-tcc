package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuCategoria implements Serializable {
	
	private static final long serialVersionUID = -3011394283478069722L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mct_cod;
	
	private String mct_nome;
	
	private Integer mct_valido;

	public Long getMct_cod() {
		return mct_cod;
	}

	public void setMct_cod(Long mct_cod) {
		this.mct_cod = mct_cod;
	}

	public String getMct_nome() {
		return mct_nome;
	}

	public void setMct_nome(String mct_nome) {
		this.mct_nome = mct_nome;
	}

	public Integer getMct_valido() {
		return mct_valido;
	}

	public void setMct_valido(Integer mct_valido) {
		this.mct_valido = mct_valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mct_cod == null) ? 0 : mct_cod.hashCode());
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
		MenuCategoria other = (MenuCategoria) obj;
		if (mct_cod == null) {
			if (other.mct_cod != null)
				return false;
		} else if (!mct_cod.equals(other.mct_cod))
			return false;
		return true;
	}
}