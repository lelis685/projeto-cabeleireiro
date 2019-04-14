package br.com.cabeleireiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cabeleireiro.domain.Cabeleireiro;
import br.com.cabeleireiro.domain.Transacao;
import br.com.cabeleireiro.repository.TransacaoRepository;

@Service
public class TransacaoServico {


	private TransacaoRepository transacaoRepository;

	@Autowired
	public TransacaoServico(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}
	
	public int existeCabeleireiro(Cabeleireiro cabeleireiro) {
		return transacaoRepository.getExisteCabeleireiro(cabeleireiro);
		
	}

	public Transacao salvarTransacao(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}

    public double somaMensal(Cabeleireiro cabeleireiro) {
    	return transacaoRepository.getSomaMensal(cabeleireiro);
    }

    public double somaAnual(Cabeleireiro cabeleireiro) {
    	return transacaoRepository.getSomaAnual(cabeleireiro);
    }
    
    public int quantidadeClientesMes(Cabeleireiro cabeleireiro) {
    	return transacaoRepository.getQuantidadeClientesAtendidosMes(cabeleireiro);
    }

    public String tempoMedioCorte(Cabeleireiro cabeleireiro) {
    	return transacaoRepository.getMediaTempoCorte(cabeleireiro);
    }

}
