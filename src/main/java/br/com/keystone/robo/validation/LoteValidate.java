package br.com.keystone.robo.validation;

import java.util.Date;

import javax.inject.Inject;

import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.keystone.robo.annotations.ALoteValidate;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.core.exceptions.VraptorValidatorException;
import br.com.keystone.robo.helper.DateHelper;
import br.com.keystone.robo.helper.ValidationHelper;
import br.com.keystone.robo.vo.DadosGeracaoLoteVO;

@ALoteValidate
public class LoteValidate implements IValidation<DadosGeracaoLoteVO, VraptorValidatorException> {
	
	@Inject
	private Validator validator;

	@Override
	public void execute(DadosGeracaoLoteVO entity) throws VraptorValidatorException {
		
		if(entity.getLot_campanhacodigo() == null){
			validator.add(new SimpleMessage("lote.lot_campanhacodigo","Uma campanha deve ser selecionada para importação dos contratos"));
		}
		
		if(entity.getLot_descontopercentual() != null && entity.getLot_descontopercentual().length() > 0){
			if(entity.getLot_descontopercentual().length() > 6){				
				validator.add(new SimpleMessage("lote.lot_descontopercentual","O campo desconto percentual deve ter no máximo 6 caracteres"));				
			}else if(!ValidationHelper.isFloatNumber(entity.getLot_descontopercentual())){
				validator.add(new SimpleMessage("lote.lot_descontopercentual","O campo desconto percentual deve conter apenas números"));
			}else if(Float.parseFloat(entity.getLot_descontopercentual()) > 100.00F){
				validator.add(new SimpleMessage("lote.lot_descontopercentual","Desconto percentual não pode ser maior que 100%"));
			}			
		}
		
		if(entity.getLot_descontohonorarios() != null && entity.getLot_descontohonorarios().length() > 0){
			if(entity.getLot_descontohonorarios().length() > 6){				
				validator.add(new SimpleMessage("lote.lot_descontohonorarios","O campo desconto honorários deve ter no máximo 6 caracteres"));				
			}else if(!ValidationHelper.isFloatNumber(entity.getLot_descontohonorarios())){
				validator.add(new SimpleMessage("lote.lot_descontohonorarios","O campo desconto honorários deve conter apenas números"));
			}else if(Float.parseFloat(entity.getLot_descontohonorarios()) > 100.00F){
				validator.add(new SimpleMessage("lote.lot_descontohonorarios","Desconto honorários não pode ser maior que 100%"));
			}			
		}
		
		if(entity.getLot_datavencimento() != null){
			try {
				if(DateHelper.compareDates(entity.getLot_datavencimento(), new Date()).equals(DateHelper.DateBefore)){
					validator.add(new SimpleMessage("lote.lot_datavencimento","A data de vencimento não pode ser anterior a data atual"));
				}
			} catch (IntranetException e) {
				validator.add(new SimpleMessage("lote.lot_datavencimento","Data de vencimento: " + e.getMessage()));
			}
		}else{
			validator.add(new SimpleMessage("lote.lot_datavencimento","A data de vencimento deve ser informada"));
		}
		
		if(entity.getLot_datapagamento() != null){
			try {
				if(DateHelper.compareDates(entity.getLot_datapagamento(), new Date()).equals(DateHelper.DateBefore)){
					validator.add(new SimpleMessage("lote.lot_datapagamento","A data de pagamento não pode ser anterior a data atual"));
				}
			} catch (IntranetException e) {
				validator.add(new SimpleMessage("lote.lot_datapagamento","Data de pagamento: " + e.getMessage()));
			}
		}else{
			validator.add(new SimpleMessage("lote.lot_datapagamento","A data de pagamento deve ser informada"));
		}
		
		if(validator.hasErrors()){
			throw new VraptorValidatorException(validator);
		}
	}
}