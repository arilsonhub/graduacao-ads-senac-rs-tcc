package br.com.keystone.robo.business.exceptions;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.model.Lote;

public class DevedorJaFoiProcessadoException extends IntranetException {
	
	private Lote lote;
	
	private static final long serialVersionUID = -8990225947501190638L;

	public DevedorJaFoiProcessadoException(String msg, Lote lote) {
		super(msg);
		this.lote = lote;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
}