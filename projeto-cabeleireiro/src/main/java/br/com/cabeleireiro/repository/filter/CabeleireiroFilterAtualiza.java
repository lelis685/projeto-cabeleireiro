package br.com.cabeleireiro.repository.filter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.cabeleireiro.domain.Endereco;

public class CabeleireiroFilterAtualiza {

	private Long id;

	@NotEmpty(message = "*Por favor preencha seu nome do estabelecimento")
	private String nomeEstabelecimento;

	@NotEmpty(message = "*Por favor preencha seu CNPJ")
	private String cnpj;

	private Endereco endereco;

	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message = "*Por favor preencha seu email")
	private String email;

	@NotEmpty(message = "*Por favor preencha seu telefone")
	private String telefone;

	private double valorAdulto;

	private double valorInfantil;

	public CabeleireiroFilterAtualiza() {
		super();
	}

	public CabeleireiroFilterAtualiza(Long id, String nomeEstabelecimento, String cnpj, Endereco endereco, String email,
			String telefone, double valorAdulto, double valorInfantil) {
		this.id = id;
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.valorAdulto = valorAdulto;
		this.valorInfantil = valorInfantil;
	}

	public Long getId() {
		return id;
	}

	public double getValorAdulto() {
		return valorAdulto;
	}

	public void setValorAdulto(double valorAdulto) {
		this.valorAdulto = valorAdulto;
	}

	public double getValorInfantil() {
		return valorInfantil;
	}

	public void setValorInfantil(double valorInfantil) {
		this.valorInfantil = valorInfantil;
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

	@Override
	public String toString() {
		return "CabeleireiroFilterAtualiza [id=" + id + ", nomeEstabelecimento=" + nomeEstabelecimento + ", cnpj="
				+ cnpj + ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + ", valorAdulto="
				+ valorAdulto + ", valorInfantil=" + valorInfantil + "]";
	}
	
	

}
