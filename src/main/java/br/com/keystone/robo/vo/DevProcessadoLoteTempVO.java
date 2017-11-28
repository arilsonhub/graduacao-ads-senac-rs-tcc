package br.com.keystone.robo.vo;

import java.io.Serializable;

public class DevProcessadoLoteTempVO implements Serializable {
	
	private static final long serialVersionUID = -5516289464922495557L;
	
	private Long dev_cod;

	private String dev_cpf_cnpj;
	
	private Long lot_cod;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lot_cod == null) ? 0 : lot_cod.hashCode());
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
		DevProcessadoLoteTempVO other = (DevProcessadoLoteTempVO) obj;
		if (lot_cod == null) {
			if (other.lot_cod != null)
				return false;
		} else if (!lot_cod.equals(other.lot_cod))
			return false;
		return true;
	}

	public Long getDev_cod() {
		return dev_cod;
	}

	public void setDev_cod(Long dev_cod) {
		this.dev_cod = dev_cod;
	}
}