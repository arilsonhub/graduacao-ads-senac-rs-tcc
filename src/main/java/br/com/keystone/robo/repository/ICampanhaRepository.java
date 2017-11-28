package br.com.keystone.robo.repository;

import java.util.List;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.vo.CampanhaVO;

public interface ICampanhaRepository {

	public List<CampanhaVO> buscarCampanhas() throws IntranetException;
}