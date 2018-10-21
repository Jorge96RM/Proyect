package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.domain.Usuario;

@Entity
public class Post {
		
	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String contenido;
	private Long nRespuestas;
	private Long nVisitas;
	
	public Long getnRespuestas() {
		return nRespuestas;
	}

	public void setnRespuestas(Long nRespuestas) {
		this.nRespuestas = nRespuestas;
	}

	public Long getnVisitas() {
		return nVisitas;
	}

	public void setnVisitas(Long nVisitas) {
		this.nVisitas = nVisitas;
	}

	@ManyToOne
	private Usuario PostSuyo;
	
	public Post(String titulo, String contenido, Usuario postSuyo) {
		super();
		this.titulo = titulo;
		this.contenido = contenido;
		this.nRespuestas = 0L;
		this.nVisitas = 0L;
		PostSuyo = postSuyo;
	}
	
	public Post() {
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
	
	public Usuario getPostSuyo() {
		return PostSuyo;
	}

	public void setPostSuyo(Usuario postSuyo) {
		PostSuyo = postSuyo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
