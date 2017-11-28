package br.com.keystone.robo.vo;

import java.io.Serializable;

public class DadosDevedorJaProcessadoVO implements Serializable {

	private static final long serialVersionUID = 2746084432548483977L;
	
	private Long dev_cod;
	
	private String dev_cpf_cnpj;
	
	private Long lot_cod;
	
	private Boolean dlt_valido;

	public Long getDev_cod() {
		return dev_cod;
	}

	public void setDev_cod(Long dev_cod) {
		this.dev_cod = dev_cod;
	}

	public String getDev_cpf_cnpj() {
		return dev_cpf_cnpj;
	}

	public void setDev_cpf_cnpj(String dev_cpf_cnpj) {
		this.dev_cpf_cnpj = dev_cpf_cnpj;
	}

	public Long getLot_cod() {
		return lot_cod;
	}

	public void setLot_cod(Long lot_cod) {
		this.lot_cod = lot_cod;
	}

	public Boolean getDlt_valido() {
		return dlt_valido;
	}

	public void setDlt_valido(Boolean dlt_valido) {
		this.dlt_valido = dlt_valido;
	}
}