package br.com.cabeleireiro.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeAbstrata<Long> {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 50, min = 3)
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Size(max = 50, min = 3)
	@Column(nullable = false)
	private String sobreNome;
	
	@NotBlank
	@Size(max = 13, min = 8)
	@Column(nullable = false)
	private String celular;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataNascimento;

	@NotBlank
	@Size(max = 13, min = 8)
	@Column(nullable = false)
	private String email;

	@NotBlank
	@Size(max = 15, min = 6)
	@Column(nullable = false)
	private String senha;
	
	public Usuario() {}
	

	public Usuario(String nome, String sobreNome, String celular, LocalDate dataNascimento, String email,
			String senha) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.celular = celular;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
