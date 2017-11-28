package br.com.keystone.robo.enums;

public enum MenuEnum {

	MenuPrincipal(1L);
	
	private final Long chave;
	
	MenuEnum(Long chaveDesejada){
		chave = chaveDesejada;
	}
	public Long getMenuChave(){
		return chave;
	}
}