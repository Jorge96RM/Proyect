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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
