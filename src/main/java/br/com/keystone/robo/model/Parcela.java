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
public class Parcela implements Serializable {
	
	private static final long serialVersionUID = 4111346753174939124L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long par_id;
	
	private Long par_cod;
	
	private Integer par_nu;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "par_div_id")
	private Divida divida;
	
	private Date par_datainclusao;
	
	public Parcela(){
		this.par_datainclusao = new Date();
	}

	public Long getPar_id() {
		return par_id;
	}

	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	public Long getPar_cod() {
		return par_cod;
	}

	public void setPar_cod(Long par_cod) {
		this.par_cod = par_cod;
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

	public Date getPar_datainclusao() {
		return par_datainclusao;
	}

	public void setPar_datainclusao(Date par_datainclusao) {
		this.par_datainclusao = par_datainclusao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((par_id == null) ? 0 : par_id.hashCode());
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
		Parcela other = (Parcela) obj;
		if (par_id == null) {
			if (other.par_id != null)
				return false;
		} else if (!par_id.equals(other.par_id))
			return false;
		return true;
	}

	public Integer getPar_nu() {
		return par_nu;
	}

	public void setPar_nu(Integer par_nu) {
		this.par_nu = par_nu;
	}
}