package br.com.keystone.robo.service;

import javax.inject.Inject;

import br.com.keystone.robo.annotations.ASimpleEmailService;
import br.com.keystone.robo.model.Lote;

public class EnvioEmailLoteService implements IEnvioEmailLoteService {

	@Inject @ASimpleEmailService
	private IEmailService emailService;
	
	@Override
	public void enviarEmailAposLoteFinalizado(Lote lote) {
		
		String subject = "O Lote :lot_cod finalizado com o status: :lot_status";
		subject        = subject.replace(":lot_cod"	  , lote.getLot_cod().toString())
								.replace(":lot_status", lote.getLoteStatus().getLts_descricao());
		
		String msg 	   = "A geração do lote :lot_cod foi finalizada com o status :lot_status";
		msg            = msg.replace(":lot_cod"	  	  , lote.getLot_cod().toString())
							.replace(":lot_status"	  , lote.getLoteStatus().getLts_descricao());
		
		emailService.enviarEmailParaTodosEmailsCadastrados(subject, msg);
	}

	@Override
	public void enviarEmailAposLotePausado(Lote lote) {		
		
		String subject = "O Lote :lot_cod entrou em pausa";
		subject        = subject.replace(":lot_cod"	  , lote.getLot_cod().toString());								
		
		String msg 	   = "A geração do lote :lot_cod foi interrompida com o status de pausa";
		msg            = msg.replace(":lot_cod"	  	  , lote.getLot_cod().toString());
		
		emailService.enviarEmailParaTodosEmailsCadastrados(subject, msg);
	}

	@Override
	public void enviarEmailAposLoteRetomado(Lote lote) {	
		
		String subject = "Lote :lot_cod foi retomado";
		subject        = subject.replace(":lot_cod"	  , lote.getLot_cod().toString());								
		
		String msg 	   = "A geração do lote :lot_cod foi retomada";
		msg            = msg.replace(":lot_cod"	  	  , lote.getLot_cod().toString());
		
		emailService.enviarEmailParaTodosEmailsCadastrados(subject, msg);
	}
}