package br.com.cabeleireiro.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Desistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Cabeleireiro cabeleireiro;

	@ManyToOne
	@JoinColumn
	private Usuario usuario;

	private double valor;

	private String motivo;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(columnDefinition = "TIMESTAMP")
	private Date dada;

	public Desistencia() {
	}

	public Desistencia(Cabeleireiro cabeleireiro, Usuario usuario, double valor, String motivo, Date dada) {
		this.cabeleireiro = cabeleireiro;
		this.usuario = usuario;
		this.valor = valor;
		this.motivo = motivo;
		this.dada = dada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cabeleireiro getCabeleireiro() {
		return cabeleireiro;
	}

	public void setCabeleireiro(Cabeleireiro cabeleireiro) {
		this.cabeleireiro = cabeleireiro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getDada() {
		return dada;
	}

	public void setDada(Date dada) {
		this.dada = dada;
	}

}
