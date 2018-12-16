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
	@Column(length=2000)
	private String contenido;
	
	private int puntos = 0;	
	private int likes = 0;
	private int dislikes = 0;
	
	@ManyToOne
	private Usuario respuestaSuya;
	
	@ManyToOne
	private Post postRespuesta;
	
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
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
