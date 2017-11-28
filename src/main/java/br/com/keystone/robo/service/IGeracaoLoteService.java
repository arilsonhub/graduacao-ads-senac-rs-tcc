package br.com.keystone.robo.service;

import java.util.List;

import br.com.keystone.robo.business.exceptions.DevedorJaFoiProcessadoException;
import br.com.keystone.robo.business.exceptions.LoteNaoPossuiDevedoresParaProcessarException;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Lote;
import br.com.keystone.robo.vo.CampanhaVO;
import br.com.keystone.robo.vo.DadosGeracaoLoteVO;

public interface IGeracaoLoteService {

	public List<CampanhaVO> buscarCampanhas() throws IntranetException;
	
	public void gerarLoteGeracaoBoleto(DadosGeracaoLoteVO dadosGeracaoLoteVO) throws IntranetException, DevedorJaFoiProcessadoException;
	
	public void retomarGeracaoLote(Lote lote) throws IntranetException;
	
	public void pausarGeracaoLote(Lote lote) throws IntranetException;
	
	public void confirmarGeracaoLote(Lote lote, Integer acao) throws IntranetException, LoteNaoPossuiDevedoresParaProcessarException;
	
	public void cancelarGeracaoLote(Lote lote) throws IntranetException;
}