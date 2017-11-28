package br.com.keystone.robo.repository;

import java.util.HashMap;
import java.util.List;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Configuracao;

public interface IConfiguracaoRepository {

	public Configuracao getConfiguracaoById(Configuracao configuracao) throws IntranetException;
	
	public HashMap<Long,Configuracao> getConfiguracoesByIds(List<Long> ids) throws IntranetException;
}