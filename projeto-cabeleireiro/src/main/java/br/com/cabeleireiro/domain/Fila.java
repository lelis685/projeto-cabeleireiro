package br.com.cabeleireiro.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Fila {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Cabeleireiro cabeleireiro;

	@ManyToOne
	@JoinColumn
	private Usuario usuario;

	@Column(name = "entrada_fila")
	@Temporal(TemporalType.TIMESTAMP)
	private Date entradaFila;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "inicio_corte", columnDefinition = "TIMESTAMP")
	private Date inicioCorte;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "fim_corte", columnDefinition = "TIMESTAMP")
	private Date fimCorte;

	@Enumerated(EnumType.STRING)
	private Status status;

	private double valor;
	
	

	public Fila(Cabeleireiro cabeleireiro, Usuario usuario, Date entradaFila, Date inicioCorte,
			Date fimCorte, Status status, double valor) {
		this.cabeleireiro = cabeleireiro;
		this.usuario = usuario;
		this.entradaFila = entradaFila;
		this.inicioCorte = inicioCorte;
		this.fimCorte = fimCorte;
		this.status = status;
		this.valor = valor;
	}

	public Fila() {
	}
	
	

	public Cabeleireiro getCabeleireiro() {
		return cabeleireiro;
	}

	public void setCabeleireiro(Cabeleireiro cabeleireiro) {
		this.cabeleireiro = cabeleireiro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getEntradaFila() {
		return entradaFila;
	}

	public void setEntradaFila(Date entradaFila) {
		this.entradaFila = entradaFila;
	}

	public Date getInicioCorte() {
		return inicioCorte;
	}

	public void setInicioCorte(Date inicioCorte) {
		this.inicioCorte = inicioCorte;
	}

	public Date getFimCorte() {
		return fimCorte;
	}

	public void setFimCorte(Date fimCorte) {
		this.fimCorte = fimCorte;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
