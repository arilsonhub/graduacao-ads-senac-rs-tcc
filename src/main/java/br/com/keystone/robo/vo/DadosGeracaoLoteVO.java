package br.com.keystone.robo.vo;

import java.io.Serializable;
import java.util.Date;

public class DadosGeracaoLoteVO implements Serializable {
	
	private static final long serialVersionUID = -1724844690853738254L;

	private Long lot_campanhacodigo;
	
	private String lot_descontopercentual;
	
	private String lot_descontohonorarios;
	
	private Date lot_datapagamento;
	
	private Date lot_datavencimento;

	public Long getLot_campanhacodigo() {
		return lot_campanhacodigo;
	}

	public void setLot_campanhacodigo(Long lot_campanhacodigo) {
		this.lot_campanhacodigo = lot_campanhacodigo;
	}

	public String getLot_descontopercentual() {
		return lot_descontopercentual;
	}

	public void setLot_descontopercentual(String lot_descontopercentual) {
		this.lot_descontopercentual = lot_descontopercentual;
	}
	
	public Date getLot_datapagamento() {
		return lot_datapagamento;
	}

	public void setLot_datapagamento(Date lot_datapagamento) {
		this.lot_datapagamento = lot_datapagamento;
	}

	public Date getLot_datavencimento() {
		return lot_datavencimento;
	}

	public void setLot_datavencimento(Date lot_datavencimento) {
		this.lot_datavencimento = lot_datavencimento;
	}

	public String getLot_descontohonorarios() {
		return lot_descontohonorarios;
	}

	public void setLot_descontohonorarios(String lot_descontohonorarios) {
		this.lot_descontohonorarios = lot_descontohonorarios;
	}
}