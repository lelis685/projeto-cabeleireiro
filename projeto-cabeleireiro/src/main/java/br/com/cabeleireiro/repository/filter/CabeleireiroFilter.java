package br.com.cabeleireiro.repository.filter;

public class CabeleireiroFilter {

	private String nomeEstabelecimento;

	private String rua;

	private String bairro;

	private String cidade;

	private String cep;

	private Integer numero;


	public CabeleireiroFilter(String nomeEstabelecimento, String rua, String bairro, String cidade, String cep,
			Integer numero) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.numero = numero;
	}

	public CabeleireiroFilter() {
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

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

	@Override
	public String toString() {
		return "CabeleireiroFilter [nomeEstabelecimento=" + nomeEstabelecimento + ", rua=" + rua + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", cep=" + cep + ", numero=" + numero + "]";
	}

	

}
