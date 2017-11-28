package br.com.keystone.robo.vo;

import java.util.Date;

public class DadosResponseServicoGeracaoBoletosVO {

	private String descricao;
	
	private Date datahora;
	
	private String processo;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}	
}