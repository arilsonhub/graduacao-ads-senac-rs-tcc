package br.com.keystone.robo.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class DadosLoteVO implements Serializable {
	
	private static final long serialVersionUID = -1465089759506516985L;
	
	private Long idLote;

	private String status;
	
	private Long statusId;
	
	private BigInteger erros;
	
	private BigInteger numeroContratos;
	
	private BigInteger numeroBoletosGerados;
	
	public DadosLoteVO(String idLote, String status, String statusId, String erros,String numeroContratos, String numeroBoletosGerados) {
		super();
		this.setIdLote(new Long(idLote));
		this.status = status;
		this.statusId = new Long(statusId);
		this.erros = new BigInteger(erros);
		this.numeroContratos = new BigInteger(numeroContratos);
		this.numeroBoletosGerados = new BigInteger(numeroBoletosGerados);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public BigInteger getErros() {
		return erros;
	}

	public void setErros(BigInteger erros) {
		this.erros = erros;
	}

	public BigInteger getNumeroContratos() {
		return numeroContratos;
	}

	public void setNumeroContratos(BigInteger numeroContratos) {
		this.numeroContratos = numeroContratos;
	}

	public BigInteger getNumeroBoletosGerados() {
		return numeroBoletosGerados;
	}

	public void setNumeroBoletosGerados(BigInteger numeroBoletosGerados) {
		this.numeroBoletosGerados = numeroBoletosGerados;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
}