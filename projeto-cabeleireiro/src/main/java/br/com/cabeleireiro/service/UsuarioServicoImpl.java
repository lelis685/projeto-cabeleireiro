package br.com.cabeleireiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabeleireiro.dao.UsuarioDAO;
import br.com.cabeleireiro.domain.Usuario;

@Service 
@Transactional
public class UsuarioServicoImpl  implements UsuarioServico{

	@Autowired
	private UsuarioDAO dao;

	@Override
	public void salva(Usuario usuario) {
		dao.salva(usuario);
	}

	@Override
	public void atualiza(Usuario usuario) {
		dao.atualiza(usuario);
	}

	@Override
	public void deleta(Long id) {
		dao.deleta(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario econtraPorId(Long id) {
		return dao.econtraPorId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> encontrarTodos() {
		return dao.encontrarTodos();
	}

	

   

}
