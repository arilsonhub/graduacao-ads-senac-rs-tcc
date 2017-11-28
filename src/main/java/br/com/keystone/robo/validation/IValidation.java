package br.com.keystone.robo.validation;

public interface IValidation<T, ValidationExceptionType extends Exception> {

	public void execute(T entity) throws ValidationExceptionType;
}