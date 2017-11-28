package br.com.keystone.robo.controllers;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;

@Controller
public class IndexController {
	
	@Public
	@Path("/")
	public void index() {
		
	}
}