package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Desistencia;

@Repository
public interface DesistenciaRepository extends JpaRepository<Desistencia, Long> {

	
	
}
