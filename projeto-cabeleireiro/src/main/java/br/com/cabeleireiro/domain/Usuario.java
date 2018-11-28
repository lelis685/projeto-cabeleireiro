package br.com.cabeleireiro.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeAbstrata<Long> {


	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotEmpty(message ="*Por favor preencha seu nome")
	private String nome;

	@Column(nullable = false, name="sobre_nome")
	@NotEmpty(message ="*Por favor preencha seu sobrenome")
	private String sobreNome;

	@Column(nullable = false)
	private String celular;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	//@NotEmpty(message ="*Por favor preencha sua data de nascimento")
	private LocalDate dataNascimento;
	


	@Column(nullable = false)
	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message ="*Por favor preencha seu email")
	private String email;

	@Column(name = "ativo")
	private int ativo;

	@Column(nullable = false)
	@Length(min = 5, message = "*Sua senha deve ter no mínimo 5 caracteres")
	@NotEmpty(message = "*Por favor preencha sua senha")
	private String senha;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_role", 
	joinColumns = @JoinColumn(name = "usuario_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public Usuario() {
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

}
