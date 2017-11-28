package br.com.keystone.robo.service;

public interface IEmailService {

	public void enviarEmailParaTodosEmailsCadastrados(String subject, String msg);
}