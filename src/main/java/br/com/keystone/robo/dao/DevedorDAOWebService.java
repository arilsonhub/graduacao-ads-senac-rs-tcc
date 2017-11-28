package br.com.keystone.robo.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import br.com.keystone.robo.annotations.ADevedorDAOWebService;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.ConfiguracaoEnum;
import br.com.keystone.robo.model.Configuracao;
import br.com.keystone.robo.model.Devedor;
import br.com.keystone.robo.model.Divida;
import br.com.keystone.robo.model.Parcela;
import br.com.keystone.robo.repository.IConfiguracaoRepository;
import br.com.keystone.robo.repository.IDevedorRepository;
import br.com.keystone.robo.vo.CampanhaVO;
import br.com.keystone.robo.vo.ListDevedorVO;

@ADevedorDAOWebService
public class DevedorDAOWebService implements IDevedorRepository {
	
	@Inject
	private IConfiguracaoRepository configuracaoRepository;

	@Override
	public List<Devedor> buscarDevedoresByCampanha(CampanhaVO campanhaVO) throws IntranetException {

		try {

			Configuracao configuracaoServicoBuscaDevedoresDaCampanha = new Configuracao();
			configuracaoServicoBuscaDevedoresDaCampanha.setCon_cod(ConfiguracaoEnum.ServicoBuscaDevedoresDaCampanha.getConfiguracaoChave());
			configuracaoServicoBuscaDevedoresDaCampanha = configuracaoRepository.getConfiguracaoById(configuracaoServicoBuscaDevedoresDaCampanha);
			
			Configuracao configuracaoLimiteDevedoresPorCampanha = new Configuracao();
		    configuracaoLimiteDevedoresPorCampanha.setCon_cod(ConfiguracaoEnum.LimiteDeDevedoresPorCampanha.getConfiguracaoChave());
		    configuracaoLimiteDevedoresPorCampanha = configuracaoRepository.getConfiguracaoById(configuracaoLimiteDevedoresPorCampanha);

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(configuracaoServicoBuscaDevedoresDaCampanha.getCon_valor());
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("fil_cod",campanhaVO.getFil_cod().toString()));		    
		    nameValuePairs.add(new BasicNameValuePair("limit",configuracaoLimiteDevedoresPorCampanha.getCon_valor()));
		    
		    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));		    
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == 200) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				XStream xtream = new XStream();
				String dateFormat = "yyyyMMdd";
				String timeFormat = "HHmmss";
				String[] acceptableFormats = {timeFormat};
				xtream.registerConverter(new DateConverter(dateFormat, acceptableFormats));
				xtream.alias("responseList", ListDevedorVO.class);
				xtream.alias("devedor", Devedor.class);
				xtream.alias("divida", Divida.class);
				xtream.alias("parcela", Parcela.class);
				ListDevedorVO xmlResponse = (ListDevedorVO) xtream.fromXML(result.toString());

				return xmlResponse.getList();

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IntranetException("Houve uma falha ao acessar o serviço de busca dos devedores da campanha");
		}
	}

	@Override
	public void atualizarDevedor(Devedor devedor) {
		// Nesta classe este método não terá implementação		
	}

	@Override
	public Devedor getDevedorByCpfCnpj(Devedor devedor) throws IntranetException {
		// Nesta classe este método não terá implementação
		return null;
	}

	@Override
	public Boolean isDeveImpedirReprocessamentoDeDevedor(Devedor devedor) {
		// Nesta classe este método não terá implementação
		return Boolean.FALSE;
	}

	@Override
	public Devedor getDevedorById(Devedor devedor) throws IntranetException {
		// Nesta classe este método não terá implementação
		return null;
	}
}