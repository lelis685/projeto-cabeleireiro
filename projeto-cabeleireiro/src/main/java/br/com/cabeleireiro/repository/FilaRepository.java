package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Fila;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {

	
	
	
}
