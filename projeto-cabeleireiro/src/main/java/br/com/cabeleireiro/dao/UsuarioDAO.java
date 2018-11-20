package br.com.cabeleireiro.dao;

import java.util.List;

import br.com.cabeleireiro.domain.Usuario;

public interface UsuarioDAO {
	
	void salva(Usuario usuario);
	void atualiza(Usuario usuario);
	void deleta(Long id);
	Usuario econtraPorId(Long id);
	List<Usuario> encontrarTodos();

}
