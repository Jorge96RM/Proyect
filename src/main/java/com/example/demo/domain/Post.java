package com.example.demo.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.example.demo.domain.Usuario;

@Entity
public class Post {
		
	@Id
	@GeneratedValue//(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String titulo;
	@Column(length=2000)
	private String contenido;
	private Long nRespuestas;
	private Long nVisitas;
	
	@Cascade(CascadeType.DELETE)
	@OneToMany(mappedBy="postRespuesta")
	private Collection<Respuesta> postrespuesta;

	@ManyToOne
	private Categoria nombre_categoria;
	
	@ManyToOne
	private Usuario PostSuyo;
	
	public Post(String titulo, String contenido, Usuario postSuyo, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.contenido = contenido;
		this.nRespuestas = 0L;
		this.nVisitas = 0L;
		this.nombre_categoria = categoria;
		PostSuyo = postSuyo;
	}
	
	public Post() {
		super();
	}
	
	public Collection<Respuesta> getPostrespuesta() {
		return postrespuesta;
	}

	public void setPostrespuesta(Collection<Respuesta> postrespuesta) {
		this.postrespuesta = postrespuesta;
	}
	
	public Categoria getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(Categoria nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

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
	
	public void addRespuesta(){
		this.nRespuestas++;
	}
}
