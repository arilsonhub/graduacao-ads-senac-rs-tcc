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
import javax.persistence.OneToMany;

@Entity
public class Devedor implements Serializable {
	
	private static final long serialVersionUID = 5751856128332479805L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dev_id;
	
	private Long dev_cod;
	
	private String dev_nome;
	
	private String dev_cpf_cnpj;
	
	private Integer dev_processado;
	
	private Date dev_datainclusao;
	
	@OneToMany(mappedBy = "devedor",fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Divida> dividas;
	
	public Devedor(){
		this.dev_processado = 0;
		this.dev_datainclusao = new Date();
	}

	public Long getDev_cod() {
		return dev_cod;
	}

	public void setDev_cod(Long dev_cod) {
		this.dev_cod = dev_cod;
	}

	public String getDev_nome() {
		return dev_nome;
	}

	public void setDev_nome(String dev_nome) {
		this.dev_nome = dev_nome;
	}

	public String getDev_cpf_cnpj() {
		return dev_cpf_cnpj;
	}

	public void setDev_cpf_cnpj(String dev_cpf_cnpj) {
		this.dev_cpf_cnpj = dev_cpf_cnpj;
	}

	public Integer getDev_processado() {
		return dev_processado;
	}

	public void setDev_processado(Integer dev_processado) {
		this.dev_processado = dev_processado;
	}

	public Date getDev_datainclusao() {
		return dev_datainclusao;
	}

	public void setDev_datainclusao(Date dev_datainclusao) {
		this.dev_datainclusao = dev_datainclusao;
	}
	
	public List<Divida> getDividas() {
		return dividas;
	}

	public void setDividas(List<Divida> dividas) {
		this.dividas = dividas;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dev_id == null) ? 0 : dev_id.hashCode());
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
		Devedor other = (Devedor) obj;
		if (dev_id == null) {
			if (other.dev_id != null)
				return false;
		} else if (!dev_id.equals(other.dev_id))
			return false;
		return true;
	}	
}