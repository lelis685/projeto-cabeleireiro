package br.com.cabeleireiro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.service.CabeleireiroServico;

@Controller
@RequestMapping("/cabeleireiros")
public class CabeleireiroController {

	
	@Autowired
	private CabeleireiroServico cabeleireiroServico;
	

	@GetMapping("/cadastrar")
	public String cadastrar(Cabeleireiro cabeleireiro) {
		return "cabeleireiro/cadastro-cabeleireiro";
	}
	
	
	
}
