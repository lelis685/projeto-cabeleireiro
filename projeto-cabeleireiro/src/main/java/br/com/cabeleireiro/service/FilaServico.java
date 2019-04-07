package br.com.cabeleireiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cabeleireiro.domain.Fila;
import br.com.cabeleireiro.repository.FilaRepository;

@Service
public class FilaServico {

	
	private FilaRepository filaRepository;
	
	
	@Autowired
	public FilaServico(FilaRepository filaRepository) {
		this.filaRepository = filaRepository;
	}
	
	@Transactional
	public Fila inserirFila(Fila fila) {
		return filaRepository.save(fila);
	}
	
	
	
	
	
	
	
	
	
	
}
