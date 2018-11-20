package br.com.cabeleireiro.service;

import java.util.List;

import br.com.cabeleireiro.domain.Usuario;

public interface UsuarioServico {
	
	void salva(Usuario usuario);
	void atualiza(Usuario usuario);
	void deleta(Long id);
	Usuario econtraPorId(Long id);
	List<Usuario> encontrarTodos();
}
