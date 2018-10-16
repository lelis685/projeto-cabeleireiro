package br.com.cabeleireiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabeleireiro.dao.CabeleiroDAO;
import br.com.cabeleireiro.domain.Cabeleleiro;

@Service 
@Transactional
public class CabeleleiroServicoImpl implements CabeleleiroServico {


	@Autowired
	private CabeleiroDAO dao;

	@Override
	public void salva(Cabeleleiro cabeleiro) {
		dao.salva(cabeleiro);
	}

	@Override
	public void atualiza(Cabeleleiro cabeleiro) {
		dao.atualiza(cabeleiro);
	}

	@Override
	public void deleta(Long id) {
		dao.deleta(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cabeleleiro econtraPorId(Long id) {
		return dao.econtraPorId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cabeleleiro> encontrarTodos() {
		return dao.encontrarTodos();
	}




}
