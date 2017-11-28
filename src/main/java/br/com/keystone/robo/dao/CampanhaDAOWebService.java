package br.com.keystone.robo.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.thoughtworks.xstream.XStream;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.enums.ConfiguracaoEnum;
import br.com.keystone.robo.model.Configuracao;
import br.com.keystone.robo.repository.ICampanhaRepository;
import br.com.keystone.robo.repository.IConfiguracaoRepository;
import br.com.keystone.robo.vo.CampanhaVO;
import br.com.keystone.robo.vo.ListCampanhaVO;

@SuppressWarnings("deprecation")
public class CampanhaDAOWebService implements ICampanhaRepository {
	
	@Inject
	private IConfiguracaoRepository configuracaoRepository;
	
	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public List<CampanhaVO> buscarCampanhas() throws IntranetException {
		
		try {
			
				Configuracao configuracao = new Configuracao();
				configuracao.setCon_cod(ConfiguracaoEnum.ServicoBuscaCampanhas.getConfiguracaoChave());				
				configuracao = configuracaoRepository.getConfiguracaoById(configuracao);
			
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(configuracao.getCon_valor());
				
		    	HttpResponse response = client.execute(post);

			    if (response.getStatusLine().getStatusCode() == 200) {
	
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
					StringBuffer result = new StringBuffer();
					String line = "";
					while ((line = rd.readLine()) != null) {
					    result.append(line);
					}
		
					XStream xtream = new XStream();
					xtream.alias("responseList", ListCampanhaVO.class);
					xtream.alias("campanha", CampanhaVO.class);
					ListCampanhaVO xmlResponse = (ListCampanhaVO) xtream.fromXML(result.toString());
					
					return xmlResponse.getList();
	
			    } else {
			    	throw new Exception();
			    }

		} catch (Exception e) {
			e.printStackTrace();
		    throw new IntranetException("Houve uma falha ao acessar o serviço de busca por campanhas");
		}
	}
}