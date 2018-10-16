package br.com.cabeleireiro.dao;

import java.util.List;

import br.com.cabeleireiro.domain.Cabeleleiro;

public interface CabeleiroDAO {

	void salva(Cabeleleiro cabeleiro);
	void atualiza(Cabeleleiro cabeleiro);
	void deleta(Long id);
	Cabeleleiro econtraPorId(Long id);
	List<Cabeleleiro> encontrarTodos();
}



