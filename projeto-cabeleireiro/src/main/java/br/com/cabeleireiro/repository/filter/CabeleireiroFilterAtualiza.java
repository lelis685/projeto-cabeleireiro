package br.com.cabeleireiro.repository.filter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.cabeleireiro.domain.Endereco;

public class CabeleireiroFilterAtualiza {

	private Long id;

	@Column(name = "nome_estabelecimento", nullable = false)
	@NotEmpty(message = "*Por favor preencha seu nome do estabelecimento")
	private String nomeEstabelecimento;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu CNPJ")
	private String cnpj;

	@Column(nullable = false)
	@Embedded
	private Endereco endereco;

	@Column(nullable = false)
	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message = "*Por favor preencha seu email")
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu telefone")
	private String telefone;

	public CabeleireiroFilterAtualiza() {
		super();
	}

	public CabeleireiroFilterAtualiza(Long id, String nomeEstabelecimento, String cnpj, Endereco endereco, String email,
			String telefone) {
		this.id = id;
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
