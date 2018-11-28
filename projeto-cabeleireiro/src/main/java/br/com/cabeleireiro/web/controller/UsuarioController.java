package br.com.cabeleireiro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.service.UsuarioServico;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		Usuario usuario = new Usuario();
		mv.addObject("usuario",usuario );
		mv.setViewName("usuario/cadastro-usuario");
		return mv;
	}
	
	
	
	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		  
		Usuario usuarioExiste = usuarioServico.encontrarUsuarioPorEmail(usuario.getEmail());
		
		if (usuarioExiste != null) {
			bindingResult
			    .rejectValue("email", "error.usuario",
			    		"Já existe um usuário com esse email");
		}
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("usuario/cadastro-usuario");
		}else {
			usuarioServico.salvarUsuario(usuario);
			mv.addObject("msgSucesso","Usuário cadastrado com sucesso.");
			mv.addObject("usuario", new Usuario());
			mv.setViewName("usuario/cadastro-usuario");
		}
		
		return mv;
	}
	
	

	
	
	
}
