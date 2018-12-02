package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.Post;

@Entity
public class Respuesta {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	private String contenido;
	
	@ManyToOne
	private Usuario respuestaSuya;
	
	@ManyToOne
	private Post postRespuesta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getRespuestaSuya() {
		return respuestaSuya;
	}

	public void setRespuestaSuya(Usuario respuestaSuya) {
		this.respuestaSuya = respuestaSuya;
	}

	public Post getPostRespuesta() {
		return postRespuesta;
	}

	public void setPostRespuesta(Post postRespuesta) {
		this.postRespuesta = postRespuesta;
	}

	public Respuesta(){
		super();
	}
	
	public Respuesta(Post postRespuesta, String contenido, Usuario respuestaSuya) {
		super();
		this.postRespuesta = postRespuesta;
		this.contenido = contenido;
		this.respuestaSuya = respuestaSuya;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
