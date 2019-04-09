package br.com.cabeleireiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cabeleireiro.domain.Transacao;
import br.com.cabeleireiro.repository.TransacaoRepository;

@Service
public class TransacaoServico {

	
	private TransacaoRepository transacaoRepository;

	@Autowired
	public TransacaoServico(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}
	
	  public Transacao salvarTransacao(Transacao transacao) {
	    	return transacaoRepository.save(transacao);
	    }
	
	
	
	
}
