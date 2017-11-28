package br.com.keystone.robo.business.exceptions;

import br.com.keystone.robo.core.exceptions.IntranetException;

public class LoteNaoPossuiDevedoresParaProcessarException extends IntranetException {
	
	private static final long serialVersionUID = 4850142602363858491L;

	public LoteNaoPossuiDevedoresParaProcessarException(String msg) {
		super(msg);		
	}
}