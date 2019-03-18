package br.com.cabeleireiro.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha sua rua")
	private String rua;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu bairro")
	private String bairro;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha sua cidade")
	private String cidade;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotEmpty(message = "*Por favor preencha seu UF")
	private UF uf;

	@Column(nullable = false, length = 9)
	@NotEmpty(message = "*Por favor preencha seu cep")
	private String cep;

	@Column(nullable = false, length = 5)
	@NotEmpty(message = "*Por favor preencha seu número")
	private Integer numero;

	@Size(max = 255)
	private String complemento;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep
				+ ", numero=" + numero + ", complemento=" + complemento + "]";
	}


}
