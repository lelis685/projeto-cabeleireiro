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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
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

	@RequestMapping(value = "/usuario/pos-login-usuario", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute CabeleireiroFilter cabeleireiroFilter) {
		System.out.println("home()");
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());
		mv.addObject("nomeUsuario", "Bem-vindo " + usuario.getNome() + ", " + usuario.getSobreNome());
		mv.addObject("cabeleireiroFilter", cabeleireiroFilter);
		mv.setViewName("usuario/pos-login-usuario");
		return mv;
	}

	@GetMapping("/pesquisa-cabeleireiro")
	public ModelAndView pesquisarCabeleireiro(@ModelAttribute CabeleireiroFilter cabeleireiroFilter, BindingResult result, Model model) {
		
		System.err.println("pesquisarCabeleireiro");
		System.err.println(cabeleireiroFilter);
		
		model.addAttribute("cabeleireiroFilter", cabeleireiroFilter);
		ModelAndView mv = new ModelAndView("usuario/pos-login-usuario");
		
		
		CabeleireiroFilter c = new CabeleireiroFilter(cabeleireiroFilter.getNomeEstabelecimento(), cabeleireiroFilter.getRua(), 
				cabeleireiroFilter.getBairro(), cabeleireiroFilter.getCidade(), cabeleireiroFilter.getCep(), cabeleireiroFilter.getNumero());
		
		List<CabeleireiroFilter> cabeleireiros = usuarioServico.filtrar(c);
		
		mv.addObject("total", cabeleireiros.size());
	
		model.addAttribute("cabeleireiros", cabeleireiros);

		System.out.println(cabeleireiros.toString());
		
		return mv;
	}

	/*
	 * 
	  @GetMapping("/show/products")
	  public String getProduct(Model model,@ModelAttribute("myFormObject") MyFormObject myFormObject, BindingResult result) {
	    
	      List<Product> products = this.productService.getAllProducts(myFormObject.getPName());
	     
	      model.addAttribute("products", products);
	      return "show_product";
	      
	      @RequestMapping(value="/person", method=RequestMethod.POST)
public String contactSubmit(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
        //errors processing
    }  
    model.addAttribute("person", person);
    return "result";
}
	      
	      
	
*/
	
	
}
