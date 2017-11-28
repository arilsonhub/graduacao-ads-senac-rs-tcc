package br.com.keystone.robo.repository;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.LoteStatus;

public interface ILoteStatusRepository {

	public LoteStatus getLoteStatusById(LoteStatus loteStatus) throws IntranetException;
}