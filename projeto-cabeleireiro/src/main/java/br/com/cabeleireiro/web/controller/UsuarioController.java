package br.com.cabeleireiro.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
import br.com.cabeleireiro.repository.filter.UsuarioFilter;
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
		mv.addObject("usuario", usuario);
		mv.setViewName("usuario/cadastro-usuario");
		return mv;
	}
	
	
	@PostMapping("/desativar/{id}")
	public ModelAndView desativar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		usuarioServico.desativarUsuario(id);
		mv.setViewName("login");
		mv.addObject("msgSucesso", "Conta desativada com sucesso.");
		return mv;
	}

	@GetMapping("/ativar")
	public ModelAndView mostrarFormAtivar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usuario/ativa-cadastro-usuario");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	
	
	@PostMapping("/ativarPost")
	public ModelAndView ativar(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		Usuario usuarioExiste = usuarioServico.encontrarUsuarioPorEmail(usuario.getEmail());

		if (usuarioExiste == null) {
			bindingResult.rejectValue("email", "error.usuario", "Não existe um usuário com esse email");
			 mv.setViewName("usuario/ativa-cadastro-usuario");
			 return mv;
		}
			usuarioServico.ativarUsuario(usuarioExiste.getEmail());
		
			mv.addObject("msgSucesso", "Usuário ativado com sucesso.");
			 mv.setViewName("usuario/ativa-cadastro-usuario");
			return mv;
	}
	
	

	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		Usuario usuarioExiste = usuarioServico.encontrarUsuarioPorEmail(usuario.getEmail());

		if (usuarioExiste != null) {
			bindingResult.rejectValue("email", "error.usuario", "Já existe um usuário com esse email");
		}
		System.out.println(usuario);
		if (bindingResult.hasErrors()) {
			mv.setViewName("usuario/cadastro-usuario");
		} else {
			usuarioServico.salvarUsuario(usuario);
			mv.addObject("msgSucesso", "Usuário cadastrado com sucesso.");
			mv.addObject("usuario", new Usuario());
			mv.setViewName("usuario/cadastro-usuario");
		}

		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView mostrarFormularioAtualizar(@PathVariable("id") Long id,Model model) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("mostrarFormularioAtualizar");
	
		Usuario usuarioEncotrado = usuarioServico.encontrarUsuarioPorId(id);
		
	
		UsuarioFilter usuario = new UsuarioFilter(usuarioEncotrado.getId(),usuarioEncotrado.getNome(), usuarioEncotrado.getSobreNome(),
				usuarioEncotrado.getCelular(), usuarioEncotrado.getDataNascimento(), usuarioEncotrado.getEmail());
		
		mv.addObject("usuario",usuario);
	
		mv.setViewName("usuario/atualiza-cadastro-usuario");
	
	    return mv;
	}
	
	@PostMapping("/atualizar/{id}")
	  public ModelAndView atualizarUsuario(@PathVariable("id") long id, @Valid UsuarioFilter usuario, BindingResult resultado) {
		ModelAndView modelAndView = new ModelAndView();
		
        if (resultado.hasErrors()) {
			usuario.setId(id);
			modelAndView.setViewName("usuario/atualiza-cadastro-usuario");
			return modelAndView;
        }
		
		usuarioServico.atualizarUsuario(id,usuario);
     
		System.out.println("atualizarUsuario controller");
        return home(new CabeleireiroFilter());
    }
	
	

	@RequestMapping(value = "/usuario/pos-login-usuario", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute CabeleireiroFilter cabeleireiroFilter) {
	
		System.out.println("home()");
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usuario/pos-login-usuario");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());
		mv.addObject("nomeUsuario", "Bem-vindo " + usuario.getNome() + ", " + usuario.getSobreNome());
		
		mv.addObject("usuario",usuario);
		System.out.println(usuario.getId());
	
		mv.addObject("cabeleireiroFilter", cabeleireiroFilter);
		
		System.out.println(usuario + "home()");
		return mv;
	}

	@GetMapping("/pesquisa-cabeleireiro")
	public ModelAndView pesquisarCabeleireiro(@ModelAttribute CabeleireiroFilter cabeleireiroFilter, Model model) {
		
		System.err.println("pesquisarCabeleireiro");
		System.err.println(cabeleireiroFilter);
		model.addAttribute("cabeleireiroFilter", cabeleireiroFilter);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());
	     
		
		ModelAndView mv = new ModelAndView("usuario/pos-login-usuario");
		mv.addObject("nomeUsuario", "Bem-vindo " + usuario.getNome() + ", " + usuario.getSobreNome());
		
		mv.addObject("usuario",usuario);

		CabeleireiroFilter c = new CabeleireiroFilter(cabeleireiroFilter.getNomeEstabelecimento(), cabeleireiroFilter.getRua(), 
				cabeleireiroFilter.getBairro(), cabeleireiroFilter.getCidade(), cabeleireiroFilter.getCep(), cabeleireiroFilter.getNumero());
		
		List<CabeleireiroFilter> cabeleireiros = usuarioServico.filtrar(c);
		
		mv.addObject("total", cabeleireiros.size());
		
	
		model.addAttribute("cabeleireiros", cabeleireiros);

		System.out.println(cabeleireiros.toString());
		
		return mv;
	}


	
	
}
