package br.com.keystone.robo.helper;

import br.com.caelum.vraptor.Result;

public class ViewHelper {
	
	private Result result;
	private static String viewDefaultPath = "WEB-INF/jsp/";
	private static String viewDefaultExtension = ".jsp";
	
	public static String setViewPath(String viewFile){
		StringBuilder str = new StringBuilder();
		str.append(viewDefaultPath);
		str.append(viewFile);
		str.append(viewDefaultExtension);
		return str.toString();
	}
	
	public static String setViewPath(String viewFilePath,String viewFile){
		StringBuilder str = new StringBuilder();
		str.append(viewDefaultPath);
		str.append(viewFilePath).append("/");		
		str.append(viewFile);
		str.append(viewDefaultExtension);
		return str.toString();
	}
	
	public void goToViewWithResult(String viewFilePath,String viewFile, Result result){
		this.result = result;
		this.result.forwardTo(setViewPath(viewFilePath,viewFile));
	}
}