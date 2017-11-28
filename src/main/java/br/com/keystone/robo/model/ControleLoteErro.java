package br.com.keystone.robo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ControleLoteErro implements Serializable {
	
	private static final long serialVersionUID = 8475242796716479449L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clt_cod;
	
	private String clt_descricao;
	
	private Date clt_datahora;
	
	private String clt_processo;
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name = "clt_lot_cod")
	private Lote lote;

	public Long getClt_cod() {
		return clt_cod;
	}

	public void setClt_cod(Long clt_cod) {
		this.clt_cod = clt_cod;
	}

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

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clt_cod == null) ? 0 : clt_cod.hashCode());
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
		ControleLoteErro other = (ControleLoteErro) obj;
		if (clt_cod == null) {
			if (other.clt_cod != null)
				return false;
		} else if (!clt_cod.equals(other.clt_cod))
			return false;
		return true;
	}	
}