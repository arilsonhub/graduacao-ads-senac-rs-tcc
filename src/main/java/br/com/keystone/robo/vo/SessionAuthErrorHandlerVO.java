package br.com.keystone.robo.vo;

import java.io.Serializable;

public class SessionAuthErrorHandlerVO implements Serializable {
	
	private static final long serialVersionUID = 4699775193618877842L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}