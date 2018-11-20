package br.com.cabeleireiro.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Cabeleireiro extends EntidadeAbstrata<Long> {

	@Column(name = "nome_estabelecimento")
	private String nomeEstabelecimento;

	private String cnpj;

	@Embedded
	private Endereco endereco;

	private String email;
	
	private String senha;
	

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
