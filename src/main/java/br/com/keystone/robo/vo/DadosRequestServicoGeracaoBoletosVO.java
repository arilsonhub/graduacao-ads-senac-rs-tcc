package br.com.keystone.robo.vo;

public class DadosRequestServicoGeracaoBoletosVO {

	private String[] Div_Cod;
	
	private String[] DataPagto;
	
	private String[] Par_Nu;
	
	private String[] Par_Cod;
	
	private String[] DescPercentual;
	
	private String[] DescHonorarios;
	
	private String[] divCodDivida;
	
	private String dataVencimentoSelecionada;

	public String[] getDiv_Cod() {
		return Div_Cod;
	}

	public void setDiv_Cod(String[] div_Cod) {
		Div_Cod = div_Cod;
	}

	public String[] getDataPagto() {
		return DataPagto;
	}

	public void setDataPagto(String[] dataPagto) {
		DataPagto = dataPagto;
	}

	public String[] getPar_Nu() {
		return Par_Nu;
	}

	public void setPar_Nu(String[] par_Nu) {
		Par_Nu = par_Nu;
	}

	public String[] getPar_Cod() {
		return Par_Cod;
	}

	public void setPar_Cod(String[] par_Cod) {
		Par_Cod = par_Cod;
	}

	public String[] getDescPercentual() {
		return DescPercentual;
	}

	public void setDescPercentual(String[] descPercentual) {
		DescPercentual = descPercentual;
	}

	public String[] getDivCodDivida() {
		return divCodDivida;
	}

	public void setDivCodDivida(String[] divCodDivida) {
		this.divCodDivida = divCodDivida;
	}

	public String getDataVencimentoSelecionada() {
		return dataVencimentoSelecionada;
	}

	public void setDataVencimentoSelecionada(String dataVencimentoSelecionada) {
		this.dataVencimentoSelecionada = dataVencimentoSelecionada;
	}

	public String[] getDescHonorarios() {
		return DescHonorarios;
	}

	public void setDescHonorarios(String[] descHonorarios) {
		DescHonorarios = descHonorarios;
	}
}