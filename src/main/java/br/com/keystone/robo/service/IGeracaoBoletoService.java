package br.com.keystone.robo.service;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Lote;

public interface IGeracaoBoletoService {

	public void execute(Lote lote) throws IntranetException;
}