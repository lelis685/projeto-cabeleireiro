package br.com.cabeleireiro.domain;

public enum Status {

	PENDENTE("0", "PENDENTE"), 
	EM_ANDAMENTO("1", "EM ANDAMENTO");

	private String codigo;
	private String descricao;

	private Status(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
