package br.com.keystone.robo.repository;

import java.util.List;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Email;

public interface IEmailRepository {

	public List<Email> getAll() throws IntranetException;
}