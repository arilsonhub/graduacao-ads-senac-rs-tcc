package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Email implements Serializable {
	
	private static final long serialVersionUID = 2338267170579463940L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long email_cod;
	
	private String email_endereco;
	
	private Integer email_valido;

	public Long getEmail_cod() {
		return email_cod;
	}

	public void setEmail_cod(Long email_cod) {
		this.email_cod = email_cod;
	}

	public String getEmail_endereco() {
		return email_endereco;
	}

	public void setEmail_endereco(String email_endereco) {
		this.email_endereco = email_endereco;
	}

	public Integer getEmail_valido() {
		return email_valido;
	}

	public void setEmail_valido(Integer email_valido) {
		this.email_valido = email_valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((email_cod == null) ? 0 : email_cod.hashCode());
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
		Email other = (Email) obj;
		if (email_cod == null) {
			if (other.email_cod != null)
				return false;
		} else if (!email_cod.equals(other.email_cod))
			return false;
		return true;
	}
}