package br.com.keystone.robo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.vo.DadosRequestServicoGeracaoBoletosVO;
import br.com.keystone.robo.vo.DadosResponseServicoGeracaoBoletosVO;

public class ServicoGeracaoBoletoWebService implements IServicoGeracaoBoletoWebService {
	
	@Override
	public DadosResponseServicoGeracaoBoletosVO gerarBoletoParaDevedor(DadosRequestServicoGeracaoBoletosVO request, String urlServico) throws IntranetException {
		
		try {
			
			if(urlServico == null) throw new IntranetException("Houve uma falha ao buscar o endereço do serviço de geração de boletos");
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlServico);
			
			Map<String, String[]> requestArrays = new HashMap<String, String[]>();
			requestArrays.put("Div_Cod[]",request.getDiv_Cod());
			requestArrays.put("DivCodDivida[]",request.getDivCodDivida());
			requestArrays.put("Par_Cod[]",request.getPar_Cod());
			requestArrays.put("Par_Nu[]",request.getPar_Nu());
			requestArrays.put("DataPagto[]",request.getDataPagto());
			requestArrays.put("DescPercentual[]",request.getDescPercentual());
			requestArrays.put("DescHonorarios[]",request.getDescHonorarios());
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();			
			for (Map.Entry<String, String[]> entry : requestArrays.entrySet()){
				if(entry.getValue() != null){
					for(String data :  entry.getValue()){
						nameValuePairs.add(new BasicNameValuePair(entry.getKey(),data));
					}
				}
			}			
			nameValuePairs.add(new BasicNameValuePair("dataVencimentoSelecionada",request.getDataVencimentoSelecionada()));
			
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
				xtream.alias("response",DadosResponseServicoGeracaoBoletosVO.class);
				return (DadosResponseServicoGeracaoBoletosVO) xtream.fromXML(result.toString());				

			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IntranetException("Houve uma falha ao acessar o serviço de geração de boletos");
		}
	}
}