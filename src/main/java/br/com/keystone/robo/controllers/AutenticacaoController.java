package br.com.keystone.robo.controllers;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.Public;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.keystone.robo.annotations.AUsuarioValidate;
import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.core.exceptions.VraptorValidatorException;
import br.com.keystone.robo.helper.ViewHelper;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.repository.IUsuarioRepository;
import br.com.keystone.robo.service.ISessaoUsuarioService;
import br.com.keystone.robo.session.SessaoUsuario;
import br.com.keystone.robo.validation.IValidation;

@Controller
public class AutenticacaoController {

	@Inject @AUsuarioValidate
	private IValidation<Usuario, VraptorValidatorException> usuarioValidate;

	@Inject
	private IUsuarioRepository usuarioRepository;
	
	@Inject
	private ISessaoUsuarioService sessaoUsuarioService;
	
	@Inject
	private Result result;
	
	@Inject 
	private SessaoUsuario sessaoUsuario; 

	@Public
	@Path("/autenticar")
	@Post
	public void executarProcessoAutenticacaoController(Usuario usuario) {

		try {
			result.include("usuario",usuario);
			usuarioValidate.execute(usuario);
			Usuario usuarioAutenticado = usuarioRepository.carregarUsuarioByLoginSenha(usuario);
			sessaoUsuarioService.montarSessaoUsuario(usuarioAutenticado);			
			result.forwardTo(ViewHelper.setViewPath("dashboard","dashBoardOperadorRobo"));

		} catch (VraptorValidatorException vee) {			
			vee.getValidator().onErrorForwardTo(ViewHelper.class).goToViewWithResult("index", "index", result);
			
		} catch (IntranetException e) {
			result.include("autenticacao_resultado", e.getMessage());
			result.forwardTo(ViewHelper.setViewPath("index", "index"));
		}
	}
	
	@Public
	@Path("/logout")
	@Get
	public void logout() {
		sessaoUsuario.setIsLogged(false);
		result.forwardTo(IndexController.class).index();
	}
}