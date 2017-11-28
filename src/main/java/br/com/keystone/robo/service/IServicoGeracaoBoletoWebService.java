package br.com.keystone.robo.service;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.vo.DadosRequestServicoGeracaoBoletosVO;
import br.com.keystone.robo.vo.DadosResponseServicoGeracaoBoletosVO;

public interface IServicoGeracaoBoletoWebService {

	public DadosResponseServicoGeracaoBoletosVO gerarBoletoParaDevedor(DadosRequestServicoGeracaoBoletosVO request, String urlServico) throws IntranetException;
}