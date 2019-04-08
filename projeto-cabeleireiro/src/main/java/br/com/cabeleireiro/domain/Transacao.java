package br.com.cabeleireiro.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Transacao {

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

	@Column(name = "entrada_fila")
	@Temporal(TemporalType.TIMESTAMP)
	private Date entradaFila;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "inicio_corte", columnDefinition = "TIMESTAMP")
	private Date inicioCorte;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "fim_corte", columnDefinition = "TIMESTAMP")
	private Date fimCorte;

	public Transacao() {
	}

	public Transacao(Cabeleireiro cabeleireiro, Usuario usuario, double valor, Date entradaFila, Date inicioCorte,
			Date fimCorte) {
		super();
		this.cabeleireiro = cabeleireiro;
		this.usuario = usuario;
		this.valor = valor;
		this.entradaFila = entradaFila;
		this.inicioCorte = inicioCorte;
		this.fimCorte = fimCorte;
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

}
