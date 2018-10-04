package br.com.cabeleireiro.domain;

import org.hibernate.validator.constraints.Length;


@SuppressWarnings("serial")
public class Usuario extends EntidadeAbstrata<Long> {

	

	private String email;
	private String nome;
	private String sobrenome;
	
	@Length(min = 5, message = "Senha precisa ser no mínimo 5 caracteres")
	private String senha;
	
    private String papel;

	







}
