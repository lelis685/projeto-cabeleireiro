package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Desistencia;

@Repository
public interface DesistenciaRepository extends JpaRepository<Desistencia, Long> {

	 @Query(
			  value = "SELECT count(1) FROM desistencia  WHERE cabeleireiro_id = ?1 and month(dada) = month(now())", 
			  nativeQuery = true)
	 int getQuantidadeDesistenciaMes(Cabeleireiro cabeleireiro);
	
}
