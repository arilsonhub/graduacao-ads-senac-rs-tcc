package br.com.keystone.robo.helper;

import java.util.regex.Pattern;

public class ValidationHelper {

	public static Boolean isFloatNumber(String param){		
		return Pattern.compile("[0-9]+").matcher(param.replace(".", "")).find();
	}
}