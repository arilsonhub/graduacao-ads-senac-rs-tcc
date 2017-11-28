package br.com.keystone.robo.core.exceptions;

import br.com.caelum.vraptor.validator.Validator;

public class VraptorValidatorException extends Exception {
	
	private static final long serialVersionUID = 3331145768552971649L;
	
	private Validator validator;

	public VraptorValidatorException(Validator validator){
		super();
		this.validator = validator;
	}

	public Validator getValidator() {
		return validator;
	}
}