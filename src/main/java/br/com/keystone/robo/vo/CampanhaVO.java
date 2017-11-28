package br.com.keystone.robo.vo;

import java.io.Serializable;

public class CampanhaVO implements Serializable {
	
	private static final long serialVersionUID = -1057758084314390473L;

	private Long fil_cod;
	
	private String fil_pro_cod;
	
	private String fil_nome;

	public Long getFil_cod() {
		return fil_cod;
	}

	public void setFil_cod(Long fil_cod) {
		this.fil_cod = fil_cod;
	}

	public String getFil_pro_cod() {
		return fil_pro_cod;
	}

	public void setFil_pro_cod(String fil_pro_cod) {
		this.fil_pro_cod = fil_pro_cod;
	}

	public String getFil_nome() {
		return fil_nome;
	}

	public void setFil_nome(String fil_nome) {
		this.fil_nome = fil_nome;
	}	
}