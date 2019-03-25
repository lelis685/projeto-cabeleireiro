package br.com.cabeleireiro.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.UF;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
import br.com.cabeleireiro.service.CabeleireiroServico;

@Controller
@RequestMapping("/cabeleireiros")
public class CabeleireiroController {

	@Autowired
	private CabeleireiroServico cabeleireiroServico;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		Cabeleireiro cabeleireiro = new Cabeleireiro();
		mv.addObject("cabeleireiro",cabeleireiro );
		mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarCabeleireiro(@Valid Cabeleireiro cabeleireiro, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		  
		Cabeleireiro cabeleireiroExiste = cabeleireiroServico.encontrarCabeleireiroPorEmail(cabeleireiro.getEmail());
		
		if (cabeleireiroExiste != null) {
			bindingResult
			    .rejectValue("email", "error.usuario",
			    		"Já existe um cabeleireiro com esse email");
		}
		
		System.out.println(cabeleireiro);
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		}else {
			cabeleireiroServico.salvarCabeleireiro(cabeleireiro);
			mv.addObject("msgSucesso","Cabeleireiro cadastrado com sucesso.");
			mv.addObject("cabeleireiro", new Cabeleireiro());
			mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		}
		
		return mv;
	}
	
	
	@RequestMapping(value = "/cabeleireiro/pos-login-cabeleireiro", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute CabeleireiroFilter cabeleireiroFilter) {
	
		System.out.println("home()");
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("cabeleireiro/pos-login-cabeleireiro");
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorEmail(auth.getName());
	
		
		mv.addObject("cabeleireiro",cabeleireiro);
		
	
		
		System.out.println(cabeleireiro);
	
		return mv;
	}
	
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
	
	
	
	
	
}
