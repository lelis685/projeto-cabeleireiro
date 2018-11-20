package br.com.cabeleireiro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.service.UsuarioServico;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioServico usuarioServico;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cabeleireiro cabeleireiro) {
		return "usuario/cadastro-usuario";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios", usuarioServico.encontrarTodos());
		return "usuario/lista-usuario"; 
	}
	
	
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "cadastro-usuario";
		}
		
		usuarioServico.salva(usuario);
		attr.addFlashAttribute("success", "Usuário cadastrado com sucesso.");
		return "redirect:/cadastrar";
	}
	
	
	
	
}
