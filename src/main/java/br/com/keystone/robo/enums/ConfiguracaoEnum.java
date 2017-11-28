package br.com.keystone.robo.enums;

public enum ConfiguracaoEnum {

	ServicoBuscaCampanhas(2L),ServicoBuscaDevedoresDaCampanha(3L), LimiteDeDevedoresPorCampanha(4L), ServicoGeracaoBoletos(5L),
	HostEnvioEmail(6L),ParametroFromEnvioEmail(7L);
	
	private final Long chave;
	
	ConfiguracaoEnum(Long chaveDesejada){
		chave = chaveDesejada;
	}
	public Long getConfiguracaoChave(){
		return chave;
	}
}