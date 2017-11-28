package br.com.keystone.robo.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.GlobalRule;
import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.keystone.robo.annotations.ARoles;
import br.com.keystone.robo.interceptor.handler.SessionAuthHandler;
import br.com.keystone.robo.session.SessaoUsuario;

@RequestScoped 
@HandledBy(SessionAuthHandler.class)
@GlobalRule
public class SessionAuthInterceptor implements CustomBrutauthRule {

		@Inject
	    private SessaoUsuario userSession;
		
		@Inject 
		private ControllerMethod method;
	    
	    public SessionAuthInterceptor() {
	        
	    }
	    
	    public boolean isAllowed() {  	
	    	if(userSession == null || !userSession.isLogged()) return false;
	    	
	    	if(method.getMethod().getAnnotation(ARoles.class) != null && userSession.getIsAdmin().equals(0)) {
	    		
	    		Boolean isAllRolesRequired = method.getMethod().getAnnotation(ARoles.class).isAllRolesRequired();
	    		
	    		if(isAllRolesRequired){
		    		List<Long> rolesList = new ArrayList<Long>();		    		
		    		for(long id : method.getMethod().getAnnotation(ARoles.class).roles()) rolesList.add(id);		    		
		    		return userSession.getPermissaoFunCodUsuario().containsAll(rolesList);
		    		
	    		}else{
	    			
	    			for(long id : method.getMethod().getAnnotation(ARoles.class).roles()){
	    				if(userSession.getPermissaoFunCodUsuario().contains(id)){
	    					return true;
	    				}
	    			}	    			
	    			return false;
	    		}
	        }
	    	
	    	return true;
	    }   
}