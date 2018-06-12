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
	
	@ManyToOne
	private Usuario PostSuyo;
	
	public Post(String titulo, Usuario postSuyo) {
		super();
		this.titulo = titulo;
		PostSuyo = postSuyo;
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
		titulo = titulo;
	}
	
	public Usuario getPostSuyo() {
		return PostSuyo;
	}

	public void setPostSuyo(Usuario postSuyo) {
		PostSuyo = postSuyo;
	}
}
