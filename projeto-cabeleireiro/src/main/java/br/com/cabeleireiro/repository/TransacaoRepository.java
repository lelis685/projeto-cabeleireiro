package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	 @Query(
			  value = "SELECT SUM(valor) FROM transacao  WHERE cabeleireiro_id = ?1 and month(entrada_fila) = month(now())", 
			  nativeQuery = true)
	 double getSomaMensal(Cabeleireiro cabeleireiro );
	 
	 @Query(
			  value = "SELECT count(1) FROM transacao  WHERE cabeleireiro_id = ?1", 
			  nativeQuery = true)
	 int getExisteCabeleireiro(Cabeleireiro cabeleireiro );

	 
	 @Query(
			  value = "SELECT SUM(valor) FROM transacao  WHERE cabeleireiro_id = ?1", 
			  nativeQuery = true)
	 double getSomaAnual(Cabeleireiro cabeleireiro);
	 
	
	 @Query(
			  value = "SELECT count(valor) FROM transacao  WHERE cabeleireiro_id = ?1 and month(entrada_fila) = month(now())", 
			  nativeQuery = true)
	 int getQuantidadeClientesAtendidosMes(Cabeleireiro cabeleireiro);
	 
	 
	 @Query(
			  value = "SELECT CAST(TIME_FORMAT(SEC_TO_TIME(round(avg((TIME_TO_SEC(TIMEDIFF(fim_corte, inicio_corte)))),2)),'%T') AS CHAR) tempo_medio FROM transacao  WHERE cabeleireiro_id = ?1", 
			  nativeQuery = true)
	 String getMediaTempoCorte(Cabeleireiro cabeleireiro);
	
	
	
	
	
}
