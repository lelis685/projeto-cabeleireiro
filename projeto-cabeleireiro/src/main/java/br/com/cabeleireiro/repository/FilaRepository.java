package br.com.cabeleireiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Fila;
import br.com.cabeleireiro.domain.Usuario;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {

	List<Fila> findByCabeleireiroOrderByEntradaFila(Cabeleireiro cabeleireiro);
	
	void deleteByUsuario(Usuario usuario);
	
	Fila findByUsuario(Usuario usuario);
	


}
