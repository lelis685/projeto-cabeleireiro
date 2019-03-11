package br.com.cabeleireiro.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "cabeleireiro", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "ck_email_unique_cab"))
public class Cabeleireiro  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "nome_estabelecimento",nullable= false)
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
	@Length(min = 5, message = "*Sua senha deve ter no mínimo 5 caracteres")
	@NotEmpty(message = "*Por favor preencha sua senha")
	private String senha;

	@Column(name = "ativo")
	private int ativo;
	
	@Column(nullable = false)
	@NotEmpty(message = "*Por favor preencha seu telefone")
	private String telefone;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles", 
			joinColumns = @JoinColumn(
					name = "usuario_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	

	public Cabeleireiro() {
	}

	public Cabeleireiro(String nomeEstabelecimento,String telefone,String cnpj,Endereco endereco,String email,String senha,int ativo, Collection<Role> roles) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.telefone = telefone;
		this.roles = roles;
	}
	
	public Cabeleireiro(String nomeEstabelecimento,String telefone,String cnpj,Endereco endereco,String email,String senha,int ativo) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.ativo = ativo;
	}
	
	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Cabeleireiro [id=" + id + ", nomeEstabelecimento=" + nomeEstabelecimento + ", cnpj=" + cnpj
				+ ", endereco=" + endereco + ", email=" + email + ", senha=" + senha + ", ativo=" + ativo + ", roles="
				+ roles + "]";
	}





}
