package br.com.cabeleireiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabeleireiros")
public class CabeleireiroController {

	/*
	@Autowired
	private CabeleireiroServico cabeleireiroServico;
	

	@GetMapping("/cadastrar")
	public String cadastrar(Cabeleireiro cabeleireiro) {
		return "cabeleireiro/cadastro-cabeleireiro";
	}*/
	
	@GetMapping("/pos-cadastro")
	public String posCadastro() {
		return "cabeleireiro/pos-login-cabeleireiro";
	}
	
	
}
