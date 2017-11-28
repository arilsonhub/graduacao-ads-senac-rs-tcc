package br.com.keystone.robo.enums;

public enum LoteStatusEnum {

	EmAndamento(1L),FalhaNoLote(2L),LoteFinalizadoComSucesso(3L),Pausado(4L);
	
	private final Long chave;
	
	LoteStatusEnum(Long chaveDesejada){
		chave = chaveDesejada;
	}
	public Long getChave(){
		return chave;
	}
}