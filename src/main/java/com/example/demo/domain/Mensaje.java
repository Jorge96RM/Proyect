package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.domain.Usuario;

@Entity
public class Mensaje {
		
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String contenido;
	private boolean leido;
	
	@ManyToOne
	private Usuario MensajeDe;
	
	@ManyToOne
	private Usuario MensajeA;
	
	public Mensaje(String titulo, String contenido, Usuario mensajeDe, Usuario mensajeA) {
		super();
		this.titulo = titulo;
		this.contenido = contenido;
		MensajeDe = mensajeDe;
		MensajeA = mensajeA;
		leido = false;
	}
	
	public Usuario getMensajeA() {
		return MensajeA;
	}

	public void setMensajeA(Usuario mensajeA) {
		MensajeA = mensajeA;
	}

	public Mensaje() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Usuario getMensajeDe() {
		return MensajeDe;
	}

	public void setMensajeDe(Usuario mensajeDe) {
		MensajeDe = mensajeDe;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}
}
