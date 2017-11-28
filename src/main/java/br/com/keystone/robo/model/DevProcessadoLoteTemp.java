package br.com.keystone.robo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DevProcessadoLoteTemp implements Serializable {
	
	private static final long serialVersionUID = 9059918755955164691L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dlt_cod;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dlt_dev_cod",referencedColumnName="dev_cod")
	private Devedor devedor;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dlt_lot_cod",referencedColumnName="lot_cod")
	private Lote lote;
	
	private Integer dlt_valido;

	public Long getDlt_cod() {
		return dlt_cod;
	}

	public void setDlt_cod(Long dlt_cod) {
		this.dlt_cod = dlt_cod;
	}

	public Devedor getDevedor() {
		return devedor;
	}

	public void setDevedor(Devedor devedor) {
		this.devedor = devedor;
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
		result = prime * result + ((dlt_cod == null) ? 0 : dlt_cod.hashCode());
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
		DevProcessadoLoteTemp other = (DevProcessadoLoteTemp) obj;
		if (dlt_cod == null) {
			if (other.dlt_cod != null)
				return false;
		} else if (!dlt_cod.equals(other.dlt_cod))
			return false;
		return true;
	}

	public Integer getDlt_valido() {
		return dlt_valido;
	}

	public void setDlt_valido(Integer dlt_valido) {
		this.dlt_valido = dlt_valido;
	}
}