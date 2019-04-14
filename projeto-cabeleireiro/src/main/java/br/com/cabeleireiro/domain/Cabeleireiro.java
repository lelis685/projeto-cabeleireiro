package br.com.cabeleireiro.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cabeleireiro", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "ck_email_unique_cab"))
public class Cabeleireiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_estabelecimento", nullable = false)
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

	@Column(nullable = false, name = "valor_adulto")
	private double valorAdulto;

	@Column(nullable = false, name = "valor_infantil")
	private double valorInfantil;


	
	@OneToMany(mappedBy="cabeleireiro",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Fila> filas  = new ArrayList<Fila>();
	
	@OneToMany(mappedBy = "cabeleireiro")
	@Fetch(FetchMode.SUBSELECT) 
	private Set<Desistencia> desistencias = new  HashSet<>();


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable
	(name = "cabeleireiros_roles", joinColumns =
	@JoinColumn(name = "cabeleireiro_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public Cabeleireiro() {
	}

	public Cabeleireiro(String nomeEstabelecimento, String telefone, String cnpj, Endereco endereco, String email,
			String senha, int ativo, Collection<Role> roles, double valorAdulto, double valorInfantil) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.telefone = telefone;
		this.roles = roles;
		this.valorAdulto = valorAdulto;
		this.valorInfantil = valorInfantil;
	}

	public Cabeleireiro(String nomeEstabelecimento, String telefone, String cnpj, Endereco endereco, String email,
			String senha, int ativo) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.ativo = ativo;
	}
	
	public Set<Desistencia> getDesistencias() {
		return desistencias;
	}

	public void setDesistencias(Set<Desistencia> desistencias) {
		this.desistencias = desistencias;
	}
	
	


	public List<Fila> getFilas() {
		return filas;
	}

	public void setFilas(List<Fila> filas) {
		this.filas = filas;
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
				+ ", endereco=" + endereco + ", email=" + email + ", ativo=" + ativo + ", telefone=" + telefone
				+ ", valorAdulto=" + valorAdulto + ", valorInfantil=" + valorInfantil + "]";
	}

	
	


}
