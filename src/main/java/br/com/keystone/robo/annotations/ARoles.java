package br.com.keystone.robo.annotations;

public @interface ARoles {

	long[] roles();	
	boolean isAllRolesRequired();
}