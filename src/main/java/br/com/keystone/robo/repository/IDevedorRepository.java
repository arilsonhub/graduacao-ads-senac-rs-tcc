package br.com.keystone.robo.repository;

import java.util.List;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.vo.CampanhaVO;

public interface IDevedorRepository {

	public List<Devedor> buscarDevedoresByCampanha(CampanhaVO campanhaVO) throws IntranetException;
	
	public void atualizarDevedor(Devedor devedor);
	
	public Devedor getDevedorByCpfCnpj(Devedor devedor) throws IntranetException;
	
	public Boolean isDeveImpedirReprocessamentoDeDevedor(Devedor devedor);
	
	public Devedor getDevedorById(Devedor devedor) throws IntranetException;
}