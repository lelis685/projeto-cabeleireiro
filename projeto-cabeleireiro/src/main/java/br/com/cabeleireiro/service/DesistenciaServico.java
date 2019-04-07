package br.com.cabeleireiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cabeleireiro.domain.Desistencia;
import br.com.cabeleireiro.repository.DesistenciaRepository;

@Service
public class DesistenciaServico {
	
	private DesistenciaRepository desistenciaRepository;
	
	@Autowired
	public DesistenciaServico(DesistenciaRepository desistenciaRepository) {
		this.desistenciaRepository = desistenciaRepository;
	}
	
    public Desistencia salvarDesistencia(Desistencia desistencia) {
    	return desistenciaRepository.save(desistencia);
    }
	
	
	
	
}
