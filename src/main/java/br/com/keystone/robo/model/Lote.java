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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Lote implements Serializable {
	
	private static final long serialVersionUID = 1570899072252196864L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lot_cod;
	
	private Long lot_campanhacodigo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lot_lts_cod",referencedColumnName="lts_cod")
	private LoteStatus loteStatus;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST , CascadeType.REMOVE})
    @JoinTable(name = "lotedevedor", joinColumns = @JoinColumn(name = "ldv_lot_cod"), inverseJoinColumns = @JoinColumn(name = "ldv_dev_id"))
	private List<Devedor> devedores;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lot_usu_cod",referencedColumnName="usu_cod")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "lote", fetch=FetchType.LAZY)
    private List<ControleLoteErro> erros;
	
	private Integer lot_valido;
	
	private String lot_descontopercentual;
	
	private String lot_descontohonorarios;
	
	private Date lot_datapagamento;
	
	private Date lot_datavencimento;
	
	private Date lot_datainclusao;
	
	public List<ControleLoteErro> getErros() {
		return erros;
	}

	public void setErros(List<ControleLoteErro> erros) {
		this.erros = erros;
	}

	public Long getLot_cod() {
		return lot_cod;
	}

	public void setLot_cod(Long lot_cod) {
		this.lot_cod = lot_cod;
	}

	public Long getLot_campanhacodigo() {
		return lot_campanhacodigo;
	}

	public void setLot_campanhacodigo(Long lot_campanhacodigo) {
		this.lot_campanhacodigo = lot_campanhacodigo;
	}
	
	public Integer getLot_valido() {
		return lot_valido;
	}

	public void setLot_valido(Integer lot_valido) {
		this.lot_valido = lot_valido;
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

	public Date getLot_datainclusao() {
		return lot_datainclusao;
	}

	public void setLot_datainclusao(Date lot_datainclusao) {
		this.lot_datainclusao = lot_datainclusao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Lote other = (Lote) obj;
		if (lot_cod == null) {
			if (other.lot_cod != null)
				return false;
		} else if (!lot_cod.equals(other.lot_cod))
			return false;
		return true;
	}

	public LoteStatus getLoteStatus() {
		return loteStatus;
	}

	public void setLoteStatus(LoteStatus loteStatus) {
		this.loteStatus = loteStatus;
	}

	public List<Devedor> getDevedores() {
		return devedores;
	}

	public void setDevedores(List<Devedor> devedores) {
		this.devedores = devedores;
	}

	public String getLot_descontohonorarios() {
		return lot_descontohonorarios;
	}

	public void setLot_descontohonorarios(String lot_descontohonorarios) {
		this.lot_descontohonorarios = lot_descontohonorarios;
	}	
}