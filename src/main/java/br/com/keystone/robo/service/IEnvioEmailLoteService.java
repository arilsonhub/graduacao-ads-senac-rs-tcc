package br.com.keystone.robo.service;

import br.com.keystone.robo.model.Lote;

public interface IEnvioEmailLoteService {

	public void enviarEmailAposLoteFinalizado(Lote lote);
	
	public void enviarEmailAposLotePausado(Lote lote);
	
	public void enviarEmailAposLoteRetomado(Lote lote);
}