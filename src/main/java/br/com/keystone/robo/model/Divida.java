package br.com.keystone.robo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Divida implements Serializable {
	
	private static final long serialVersionUID = 4078143601892102993L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long div_id;
	
	private Long div_cod;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "div_dev_cod")
	private Devedor devedor;
	
	@OneToMany(mappedBy = "divida",fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Parcela> parcelas;
	
	private Date div_datainclusao;

	public Divida(){
		this.div_datainclusao = new Date();
	}
	
	public Long getDiv_id() {
		return div_id;
	}

	public void setDiv_id(Long div_id) {
		this.div_id = div_id;
	}

	public Long getDiv_cod() {
		return div_cod;
	}

	public void setDiv_cod(Long div_cod) {
		this.div_cod = div_cod;
	}

	public Devedor getDevedor() {
		return devedor;
	}

	public void setDevedor(Devedor devedor) {
		this.devedor = devedor;
	}

	public Date getDiv_datainclusao() {
		return div_datainclusao;
	}

	public void setDiv_datainclusao(Date div_datainclusao) {
		this.div_datainclusao = div_datainclusao;
	}
	
	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((div_id == null) ? 0 : div_id.hashCode());
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
		Divida other = (Divida) obj;
		if (div_id == null) {
			if (other.div_id != null)
				return false;
		} else if (!div_id.equals(other.div_id))
			return false;
		return true;
	}	
}