package br.com.cabeleireiro.repository.filter;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class UsuarioFilter {

	private Long id;

	@NotEmpty(message = "*Por favor preencha seu nome")
	private String nome;

	@NotEmpty(message = "*Por favor preencha seu sobrenome")
	private String sobreNome;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu celular")
	private String celular;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "*Por favor preencha sua data de nascimento")
	private LocalDate dataNascimento;

	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message = "*Por favor preencha seu email")
	private String email;

	public UsuarioFilter() {
	}

	public UsuarioFilter(Long id, String nome, String sobreNome, String celular, LocalDate dataNascimento, String email
			) {
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.celular = celular;
		this.dataNascimento = dataNascimento;
		this.email = email;
		
	}


	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
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



}
