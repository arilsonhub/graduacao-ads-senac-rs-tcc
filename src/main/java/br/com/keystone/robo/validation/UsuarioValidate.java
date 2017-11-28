package br.com.keystone.robo.validation;

import javax.inject.Inject;

import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.keystone.robo.annotations.AUsuarioValidate;
import br.com.keystone.robo.core.exceptions.VraptorValidatorException;
import br.com.keystone.robo.model.Usuario;

@AUsuarioValidate
public class UsuarioValidate implements IValidation<Usuario, VraptorValidatorException>  {

	@Inject
	private Validator validator;	
	
	@Override
	public void execute(Usuario entity) throws VraptorValidatorException {
		
		if(entity.getUsu_usuario() == null || entity.getUsu_usuario().length() == 0){
			validator.add(new SimpleMessage("usuario.usu_usuario","Por favor, informe seu usuário"));
		}
		
		if(entity.getUsu_senha() == null || entity.getUsu_senha().length() == 0){
			validator.add(new SimpleMessage("usuario.usu_senha","Por favor, informe sua senha"));
		}		
		
		if(validator.hasErrors()){
			throw new VraptorValidatorException(validator);
		}		
	}
}