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

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Fila;
import br.com.cabeleireiro.domain.Status;
import br.com.cabeleireiro.domain.Transacao;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilterAtualiza;
import br.com.cabeleireiro.service.CabeleireiroServico;
import br.com.cabeleireiro.service.DesistenciaServico;
import br.com.cabeleireiro.service.FilaServico;
import br.com.cabeleireiro.service.TransacaoServico;
import br.com.cabeleireiro.util.FormatarData;

@Controller
@RequestMapping("/cabeleireiros")
public class CabeleireiroController {

	@Autowired
	private CabeleireiroServico cabeleireiroServico;

	@Autowired
	private FilaServico filaServico;

	@Autowired
	private TransacaoServico transacaoServico;
	
	@Autowired
	private DesistenciaServico desistenciaServico;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		Cabeleireiro cabeleireiro = new Cabeleireiro();
		mv.addObject("cabeleireiro", cabeleireiro);
		mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		return mv;
	}
	
	@GetMapping("/relatorio")
	public ModelAndView relatorio() {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorEmail(auth.getName());
		
		int existeCabeleireiro = transacaoServico.existeCabeleireiro(cabeleireiro);
		
		if(existeCabeleireiro > 0) {
			double somaMensal = transacaoServico.somaMensal(cabeleireiro);
			mv.addObject("somaMensal", somaMensal);
			
			double somaAnual = transacaoServico.somaAnual(cabeleireiro);
			mv.addObject("somaAnual", somaAnual);
			
			int quantidadeClientesMes = transacaoServico.quantidadeClientesMes(cabeleireiro);
			mv.addObject("quantidadeClientesMes", quantidadeClientesMes);
			
			int quantidadeDesistenciaMes = desistenciaServico.quantidadeDesistenciaMes(cabeleireiro);
			mv.addObject("quantidadeDesistenciaMes", quantidadeDesistenciaMes);
			
			String tempoMedioCorte = transacaoServico.tempoMedioCorte(cabeleireiro);
			mv.addObject("tempoMedioCorte", tempoMedioCorte);
			
			mv.addObject("invalido", "");
		}else {
			mv.addObject("somaMensal", 0);
			mv.addObject("somaAnual", 0);
			mv.addObject("quantidadeClientesMes", 0);
			mv.addObject("quantidadeDesistenciaMes", desistenciaServico.quantidadeDesistenciaMes(cabeleireiro));
			mv.addObject("tempoMedioCorte", "00:00:00");
			mv.addObject("invalido", "Não foi realizado nenhum corte até o momento");
		}
		
		
		
		mv.setViewName("cabeleireiro/relatorio");
		
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarCabeleireiro(@Valid Cabeleireiro cabeleireiro, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		Cabeleireiro cabeleireiroExiste = cabeleireiroServico.encontrarCabeleireiroPorEmail(cabeleireiro.getEmail());

		if (cabeleireiroExiste != null) {
			bindingResult.rejectValue("email", "error.usuario", "Já existe um cabeleireiro com esse email");
		}

		System.out.println(cabeleireiro);

		if (bindingResult.hasErrors()) {
			mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		} else {
			cabeleireiroServico.salvarCabeleireiro(cabeleireiro);
			mv.addObject("msgSucesso", "Cabeleireiro cadastrado com sucesso.");
			mv.addObject("cabeleireiro", new Cabeleireiro());
			mv.setViewName("cabeleireiro/cadastro-cabeleireiro");
		}

		return mv;
	}

	@RequestMapping(value = "/cabeleireiro/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute CabeleireiroFilter cabeleireiroFilter) {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("cabeleireiro/home");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorEmail(auth.getName());

		List<Fila> filaPorCabeleireiro = filaServico.getFilaPorCabeleireiro(cabeleireiro);

		mv.addObject("fila", filaPorCabeleireiro);
		mv.addObject("nomeCabeleireiro",cabeleireiro.getNomeEstabelecimento());

		mv.addObject("cabeleireiro", cabeleireiro);

		System.out.println(cabeleireiro);

		return mv;
	}

	@PostMapping("/inicia")
	public String inicio() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorEmail(auth.getName());
		
		Fila primeiroUsuario = filaServico.getPrimeiroUsuario(cabeleireiro);
		
		System.err.println(primeiroUsuario);
		
		if(primeiroUsuario == null || primeiroUsuario.getStatus().equals(Status.EM_ANDAMENTO)) {
			return "redirect:/cabeleireiros/cabeleireiro/home?errorInicio=true";
		}
		
		if(primeiroUsuario.getStatus().equals(Status.PENDENTE) && primeiroUsuario != null) {
			filaServico.iniciaCorte(cabeleireiro);
		}
		
		return "redirect:/cabeleireiros/cabeleireiro/home";
	}

	@PostMapping("/finaliza")
	public String fim() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorEmail(auth.getName());

		Fila primeiroUsuario = filaServico.getPrimeiroUsuario(cabeleireiro);
		
		if(primeiroUsuario == null || primeiroUsuario.getStatus().equals(Status.PENDENTE)) {
			return  "redirect:/cabeleireiros/cabeleireiro/home?error=true";
		}

		if(primeiroUsuario.getStatus().equals(Status.EM_ANDAMENTO) && primeiroUsuario != null) {
			transacaoServico.salvarTransacao(new Transacao(cabeleireiro, primeiroUsuario.getUsuario(),
					primeiroUsuario.getValor(), primeiroUsuario.getEntradaFila(), primeiroUsuario.getInicioCorte(),
					FormatarData.formataData()));
			
			filaServico.sairFila(primeiroUsuario.getUsuario());
		}
		

		return "redirect:/cabeleireiros/cabeleireiro/home";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView mostrarFormularioAtualizar(@PathVariable("id") Long id, Model model) {
		ModelAndView mv = new ModelAndView();

		Cabeleireiro cabeleireiroEncotrado = cabeleireiroServico.encontrarCabeleireiroPorId(id);

		CabeleireiroFilterAtualiza cabeleireiro = new CabeleireiroFilterAtualiza(cabeleireiroEncotrado.getId(),
				cabeleireiroEncotrado.getNomeEstabelecimento(), cabeleireiroEncotrado.getCnpj(),
				cabeleireiroEncotrado.getEndereco(), cabeleireiroEncotrado.getEmail(),
				cabeleireiroEncotrado.getTelefone(), cabeleireiroEncotrado.getValorAdulto(),
				cabeleireiroEncotrado.getValorInfantil());
	

		mv.addObject("cabeleireiro", cabeleireiro);
		
		System.err.println(cabeleireiro.getValorAdulto());

		mv.setViewName("cabeleireiro/atualiza-cadastro-cabeleireiro");

		return mv;
	}

	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarCabeleireiro(@PathVariable("id") long id,
			@Valid CabeleireiroFilterAtualiza cabeleireiroFilterAtualiza, BindingResult resultado) {
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cabeleireiro/atualiza-cadastro-cabeleireiro");
		
		modelAndView.addObject("cabeleireiro",
				cabeleireiroServico.encontrarCabeleireiroPorEmail(cabeleireiroFilterAtualiza.getEmail()));
		if (resultado.hasErrors()) {
			cabeleireiroFilterAtualiza.setId(id);
			modelAndView.setViewName("cabeleireiro/atualiza-cadastro-cabeleireiro");
			return modelAndView;
		}

		cabeleireiroServico.atualizarCabeleireiro(id, cabeleireiroFilterAtualiza);
		modelAndView.addObject("msgSucesso", "Dados atualizados com sucesso.");

		return modelAndView;
	}

	@PostMapping("/desativar/{id}")
	public ModelAndView desativar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		cabeleireiroServico.desativarCabeleireiro(id);
		mv.setViewName("login");
		mv.addObject("msgSucesso", "Conta desativada com sucesso.");
		return mv;
	}

}
