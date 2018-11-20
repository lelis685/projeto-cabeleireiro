package br.com.cabeleireiro.service;

import java.util.List;

import br.com.cabeleireiro.domain.Cabeleireiro;

public interface CabeleireiroServico {
	void salva(Cabeleireiro cabeleiro);
	void atualiza(Cabeleireiro cabeleiro);
	void deleta(Long id);
	Cabeleireiro econtraPorId(Long id);
	List<Cabeleireiro> encontrarTodos();
}
