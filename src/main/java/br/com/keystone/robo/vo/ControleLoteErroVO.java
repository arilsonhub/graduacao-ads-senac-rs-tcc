package br.com.keystone.robo.vo;

import java.io.Serializable;
import java.util.Date;

public class ControleLoteErroVO implements Serializable {
	
	private static final long serialVersionUID = 2707199694763235271L;

	private String clt_descricao;
	
	private Date clt_datahora;
	
	private String clt_processo;

	public String getClt_descricao() {
		return clt_descricao;
	}

	public void setClt_descricao(String clt_descricao) {
		this.clt_descricao = clt_descricao;
	}

	public Date getClt_datahora() {
		return clt_datahora;
	}

	public void setClt_datahora(Date clt_datahora) {
		this.clt_datahora = clt_datahora;
	}

	public String getClt_processo() {
		return clt_processo;
	}

	public void setClt_processo(String clt_processo) {
		this.clt_processo = clt_processo;
	}
}