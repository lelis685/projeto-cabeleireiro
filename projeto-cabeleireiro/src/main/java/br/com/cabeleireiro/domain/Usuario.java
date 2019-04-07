package br.com.cabeleireiro.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "ck_email_unique"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu nome")
	private String nome;

	@Column(nullable = false, name = "sobre_nome")
	@NotEmpty(message = "*Por favor preencha seu sobrenome")
	private String sobreNome;

	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu celular")
	private String celular;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", columnDefinition = "DATE")
	@NotNull(message = "*Por favor preencha sua data de nascimento")
	private LocalDate dataNascimento;

	@Column(nullable = false)
	@Email(message = "*Por favor preencha um email válido")
	@NotEmpty(message = "*Por favor preencha seu email")
	private String email;

	@Column(name = "ativo")
	private int ativo;

	@Column(nullable = false)
	@Length(min = 5, message = "*Sua senha deve ter no mínimo 5 caracteres")
	@NotEmpty(message = "*Por favor preencha sua senha")
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	@OneToMany(mappedBy = "usuario")
	@Fetch(FetchMode.SUBSELECT) 
	private Set<Fila> filas = new HashSet<Fila>();
	
	@OneToMany(mappedBy = "usuario")
	@Fetch(FetchMode.SUBSELECT) 
	private Set<Desistencia> desistencias = new  HashSet<>();

	public Usuario() {
	}

	public Usuario(Long id) {
		super();
		this.id = id;
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

	public Usuario(String nome, String sobreNome, String celular, LocalDate dataNascimento, String email, String senha,
			Collection<Role> roles) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.celular = celular;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public Set<Fila> getFilas() {
		return filas;
	}

	public void setFilas(Set<Fila> filas) {
		this.filas = filas;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public int getAtivo() {
		return ativo;
	}
	
	

	public Set<Desistencia> getDesistencias() {
		return desistencias;
	}

	public void setDesistencias(Set<Desistencia> desistencias) {
		this.desistencias = desistencias;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobreNome=" + sobreNome + ", celular=" + celular
				+ ", dataNascimento=" + dataNascimento + ", email=" + email + ", ativo=" + ativo + ", senha=" + senha
				+ ", roles=" + roles + "]";
	}

}
