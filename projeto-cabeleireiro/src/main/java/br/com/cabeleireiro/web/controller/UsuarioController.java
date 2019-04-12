package br.com.cabeleireiro.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import br.com.cabeleireiro.domain.Desistencia;
import br.com.cabeleireiro.domain.Fila;
import br.com.cabeleireiro.domain.Status;
import br.com.cabeleireiro.domain.Usuario;
import br.com.cabeleireiro.repository.filter.CabeleireiroFilter;
import br.com.cabeleireiro.repository.filter.UsuarioFilter;
import br.com.cabeleireiro.service.CabeleireiroServico;
import br.com.cabeleireiro.service.DesistenciaServico;
import br.com.cabeleireiro.service.FilaServico;
import br.com.cabeleireiro.service.UsuarioServico;
import br.com.cabeleireiro.util.FormatarData;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private CabeleireiroServico cabeleireiroServico;

	@Autowired
	private FilaServico filaServico;
	
	@Autowired
	private DesistenciaServico desistenciaServico;

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
	

	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		Usuario usuarioExiste = usuarioServico.encontrarUsuarioPorEmail(usuario.getEmail());

		if (usuarioExiste != null) {
			bindingResult.rejectValue("email", "error.usuario", "Já existe um usuário com esse email");
		}
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
	
	

	@PostMapping("/sair/{id}")
	public String sairFila(@PathVariable("id") Long id,HttpServletRequest req) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());

		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorId(id);

		Fila usuarioExiste = filaServico.usuarioExiste(usuario);
		
		if (usuarioExiste != null) {
			desistenciaServico.salvarDesistencia(new Desistencia(cabeleireiro, usuario
					, usuarioExiste.getValor(),req.getParameter("motivo"), FormatarData.formataData()));
			
			filaServico.sairFila(usuarioExiste.getUsuario());
		}
		
		return "redirect:/usuarios/fila/" + id;
	}
	
	
	@GetMapping("/fila/{id}")
	public ModelAndView mostrarPagFila(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("usuario/fila");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());

		Cabeleireiro cabeleireiroEncontrado = cabeleireiroServico.encontrarCabeleireiroPorId(id);
		List<Fila> filaPorCabeleireiro = filaServico.getFilaPorCabeleireiro(cabeleireiroEncontrado);
		

		mv.addObject("fila", filaPorCabeleireiro);
		mv.addObject("cabeleireiro", cabeleireiroEncontrado);
		mv.addObject("usuario", usuario);
		mv.addObject("nome", "Bem-vindo ao " + cabeleireiroEncontrado.getNomeEstabelecimento());

		mv.addObject("infantil", +cabeleireiroEncontrado.getValorInfantil());
		mv.addObject("adulto", +cabeleireiroEncontrado.getValorAdulto());
	
		Fila usuarioExiste = filaServico.usuarioExiste(usuario);
		
		if (usuarioExiste != null) {
			mv.addObject("error","Você está na fila do "+ usuarioExiste.getCabeleireiro().getNomeEstabelecimento());
			return mv;
		}

		return mv;
	}

	@PostMapping("/fila/{id}")
	public ModelAndView gravarFila(@PathVariable("id") Long id, HttpServletRequest req) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());

		Cabeleireiro cabeleireiro = cabeleireiroServico.encontrarCabeleireiroPorId(id);

		Fila usuarioExiste = filaServico.usuarioExiste(usuario);

		if (usuarioExiste != null) {
			return mostrarPagFila(id);
		}

		Date data =FormatarData.formataData();


		Fila fila = new Fila(cabeleireiro, usuario, data, null, Status.PENDENTE,
				Double.parseDouble(req.getParameter("valor")));
		

		filaServico.inserirFila(fila);


		return mostrarPagFila(id);
	}


	@GetMapping("/editar/{id}")
	public ModelAndView mostrarFormularioAtualizar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();

		Usuario usuarioEncotrado = usuarioServico.encontrarUsuarioPorId(id);

		UsuarioFilter usuario = new UsuarioFilter(usuarioEncotrado.getId(), usuarioEncotrado.getNome(),
				usuarioEncotrado.getSobreNome(), usuarioEncotrado.getCelular(), usuarioEncotrado.getDataNascimento(),
				usuarioEncotrado.getEmail());

		mv.addObject("usuario", usuario);

		mv.setViewName("usuario/atualiza-cadastro-usuario");

		return mv;
	}

	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarUsuario(@PathVariable("id") Long id, @Valid UsuarioFilter usuario,
			BindingResult resultado) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usuario/atualiza-cadastro-usuario");
		modelAndView.addObject("usuario", usuario);
		if (resultado.hasErrors()) {
			usuario.setId(id);
			return modelAndView;
		}
		usuarioServico.atualizarUsuario(id, usuario);

		modelAndView.addObject("msgSucesso", "Dados atualizados com sucesso.");
		return modelAndView;
	}

	@RequestMapping(value = "/usuario/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute CabeleireiroFilter cabeleireiroFilter) {

		ModelAndView mv = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());

		mv.addObject("nomeUsuario",usuario.getNome() + ", " + usuario.getSobreNome());

		mv.addObject("usuario", usuario);

		mv.addObject("cabeleireiroFilter", cabeleireiroFilter);

		mv.setViewName("usuario/home");

		return mv;
	}

	@GetMapping("/pesquisa-cabeleireiro")
	public ModelAndView pesquisarCabeleireiro(@ModelAttribute CabeleireiroFilter cabeleireiroFilter, Model model) {

		model.addAttribute("cabeleireiroFilter", cabeleireiroFilter);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioServico.encontrarUsuarioPorEmail(auth.getName());

		ModelAndView mv = new ModelAndView("usuario/home");
		mv.addObject("nomeUsuario",usuario.getNome() + ", " + usuario.getSobreNome());

		mv.addObject("usuario", usuario);

		CabeleireiroFilter c = new CabeleireiroFilter(cabeleireiroFilter.getId(),
				cabeleireiroFilter.getNomeEstabelecimento(), cabeleireiroFilter.getRua(),
				cabeleireiroFilter.getBairro(), cabeleireiroFilter.getCidade(), cabeleireiroFilter.getCep(),
				cabeleireiroFilter.getRegiao(), cabeleireiroFilter.getComplemento(), cabeleireiroFilter.getNumero());

		List<CabeleireiroFilter> cabeleireiros = usuarioServico.filtrar(c);

		mv.addObject("total", cabeleireiros.size());

		model.addAttribute("cabeleireiros", cabeleireiros);

		return mv;
	}

}
