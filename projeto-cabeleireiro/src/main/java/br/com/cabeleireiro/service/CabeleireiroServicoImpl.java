package br.com.cabeleireiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabeleireiro.domain.Cabeleireiro;

@Service 
@Transactional
public class CabeleireiroServicoImpl implements CabeleireiroServico {


	@Autowired
	private CabeleireiroServico dao;

	@Override
	public void salva(Cabeleireiro cabeleireiro) {
		dao.salva(cabeleireiro);
	}

	@Override
	public void atualiza(Cabeleireiro cabeleiro) {
		dao.atualiza(cabeleiro);
	}

	@Override
	public void deleta(Long id) {
		dao.deleta(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cabeleireiro econtraPorId(Long id) {
		return dao.econtraPorId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cabeleireiro> encontrarTodos() {
		return dao.encontrarTodos();
	}




}
