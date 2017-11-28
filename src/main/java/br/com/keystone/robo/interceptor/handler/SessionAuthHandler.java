package br.com.keystone.robo.interceptor.handler;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.keystone.robo.controllers.IndexController;
import br.com.keystone.robo.vo.SessionAuthErrorHandlerVO;

@RequestScoped
public class SessionAuthHandler implements RuleHandler{
	
		@Inject
	    private Result result;	    
	    
		@Inject
	    private HttpServletRequest request;
		
		public SessionAuthHandler(){
			
		}
		
	    @Override
	    public void handle() {
	    	
	    	SessionAuthErrorHandlerVO errorHandlerVO = new SessionAuthErrorHandlerVO();
    		errorHandlerVO.setErrorMessage("Acesso negado");
	    	
	    	if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
	    		result.use(Results.json()).from(errorHandlerVO).serialize();	    		
	    	}else{
	    		result.include("autenticacao_resultado",errorHandlerVO.getErrorMessage());
	    		result.forwardTo(IndexController.class).index();
	    	}	        
	    }
}