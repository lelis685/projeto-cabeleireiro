package br.com.cabeleireiro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.filter.UsuarioFilterAtivar;
import br.com.cabeleireiro.service.CabeleireiroServico;
import br.com.cabeleireiro.service.UsuarioServico;

@Controller
public class HomeController {

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private CabeleireiroServico cabeleireiroServico;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/ativa-cadastro-usuario")
	public ModelAndView mostrarFormAtivar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ativa-cadastro-usuario");
		mv.addObject("usuario", new UsuarioFilterAtivar());
		return mv;
	}

	
	@RequestMapping(value={ "/ativa-cadastro-usuario"}, method = RequestMethod.POST)
	public ModelAndView ativar(@Valid UsuarioFilterAtivar usuario,BindingResult bindingResult) {
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ativa-cadastro-usuario");
		mv.addObject("usuario", new UsuarioFilterAtivar());

		Usuario usuarioClienteExiste = usuarioServico.encontrarUsuarioPorEmail(usuario.getEmail());
		Cabeleireiro usuarioCabeleireiroExiste = cabeleireiroServico.encontrarCabeleireiroPorEmail(usuario.getEmail());
		

		if (usuarioClienteExiste == null && usuarioCabeleireiroExiste == null) {
			mv.addObject("error", "Não existe um usuário com esse email");
			return mv;
		}

		if(usuarioClienteExiste != null) {
			usuarioServico.ativarUsuario(usuario.getEmail());
		}

		if(usuarioCabeleireiroExiste != null) {
			cabeleireiroServico.ativarCabeleireiro(usuario.getEmail());
		}
		mv.addObject("msgSucesso", "Usuário ativado com sucesso.");

		
		return mv;
	}





}
