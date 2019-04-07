package br.com.cabeleireiro.repository.filter;

public class CabeleireiroFilter {

	private long id;

	private String nomeEstabelecimento;

	private String rua;

	private String bairro;

	private String cidade;

	private String cep;

	private String regiao;

	private String complemento;

	private Integer numero;

	public CabeleireiroFilter(long id, String nomeEstabelecimento, String rua, String bairro, String cidade, String cep,
			String regiao, String complemento, Integer numero) {
		this.id = id;
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.regiao = regiao;
		this.numero = numero;
		this.complemento = complemento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
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
