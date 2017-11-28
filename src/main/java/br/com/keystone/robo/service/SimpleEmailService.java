package br.com.keystone.robo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.keystone.robo.annotations.ASimpleEmailService;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.ConfiguracaoEnum;
import br.com.keystone.robo.model.Configuracao;
import br.com.keystone.robo.model.Email;
import br.com.keystone.robo.repository.IConfiguracaoRepository;
import br.com.keystone.robo.repository.IEmailRepository;

@ASimpleEmailService
public class SimpleEmailService implements IEmailService {

	@Inject
	private IConfiguracaoRepository configuracaoRepository;
	
	@Inject
	private IEmailRepository emailRepository;	
	
	private SimpleEmail simpleEmail;
	
	private void setConfiguracoesEmail() throws EmailException, IntranetException {
		
		this.simpleEmail = new SimpleEmail();
		
		List<Long> parametrosEmail = new ArrayList<Long>();
		parametrosEmail.add(ConfiguracaoEnum.HostEnvioEmail.getConfiguracaoChave());
		parametrosEmail.add(ConfiguracaoEnum.ParametroFromEnvioEmail.getConfiguracaoChave());
		
		HashMap<Long, Configuracao> config = configuracaoRepository.getConfiguracoesByIds(parametrosEmail);		
		simpleEmail.setHostName(config.get(ConfiguracaoEnum.HostEnvioEmail.getConfiguracaoChave()).getCon_valor());
		simpleEmail.setFrom(config.get(ConfiguracaoEnum.ParametroFromEnvioEmail.getConfiguracaoChave()).getCon_valor(), "Robô KeyStone");
	}
	
	@Override
	public void enviarEmailParaTodosEmailsCadastrados(String subject, String msg) {
		try {
			this.setConfiguracoesEmail();
			this.addEmails();			
			simpleEmail.setSubject(subject);
			simpleEmail.setMsg(msg);
			simpleEmail.send();
		}catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	private void addEmails() throws EmailException, IntranetException{		
		for(Email email : emailRepository.getAll()){
			simpleEmail.addTo(email.getEmail_endereco());
		}
	}
}