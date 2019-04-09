package br.com.cabeleireiro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Fila;
import br.com.cabeleireiro.domain.Status;
import br.com.cabeleireiro.domain.Usuario;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {
	
	
	@Modifying
	@Query("update Fila u set u.status = ?1, u.inicioCorte= ?2  where u.usuario = ?3 and u.cabeleireiro = ?4")
	void iniciaCorte(Status status,Date date,Usuario usuario,Cabeleireiro cabeleireiro);

	Fila findFirst1ByCabeleireiroOrderByEntradaFilaAsc(Cabeleireiro cabeleireiro);
	
	List<Fila> findByCabeleireiroOrderByEntradaFilaAsc(Cabeleireiro cabeleireiro);
	
	void deleteByUsuario(Usuario usuario);
	
	Fila findByUsuario(Usuario usuario);
	


}
