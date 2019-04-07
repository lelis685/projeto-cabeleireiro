package br.com.cabeleireiro.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "entrada_fila", columnDefinition = "DATE")
	private LocalDate entradaFila;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "inicio_corte", columnDefinition = "DATE")
	private LocalDate inicioCorte;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fim_corte", columnDefinition = "DATE")
	private LocalDate fimCorte;

	@Enumerated(EnumType.STRING)
	private Status status;

	private double valor;
	
	

	public Fila(Cabeleireiro cabeleireiro, Usuario usuario, LocalDate entradaFila, LocalDate inicioCorte,
			LocalDate fimCorte, Status status, double valor) {
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

	public LocalDate getEntradaFila() {
		return entradaFila;
	}

	public void setEntradaFila(LocalDate entradaFila) {
		this.entradaFila = entradaFila;
	}

	public LocalDate getInicioCorte() {
		return inicioCorte;
	}

	public void setInicioCorte(LocalDate inicioCorte) {
		this.inicioCorte = inicioCorte;
	}

	public LocalDate getFimCorte() {
		return fimCorte;
	}

	public void setFimCorte(LocalDate fimCorte) {
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
