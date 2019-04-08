package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Transacao;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
