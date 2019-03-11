package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;

@Repository
public interface CabeleireiroRepository extends JpaRepository<Cabeleireiro, Long>{

	Cabeleireiro findByEmail(String email);
	
	
}
