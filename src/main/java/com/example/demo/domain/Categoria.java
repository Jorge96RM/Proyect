package com.example.demo.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.domain.Post;

@Entity

@Table(name = "categoria", schema="proyecto")
public class Categoria {
	
	public static final String REDACCIONES = "Redacciones";
	public static final String CURRICULUMS = "Curriculums";
	public static final String CARTAS = "Cartas";
	public static final String OTROSC = "OtrosC";
	public static final String EXPRESIONES = "Expresiones";
	public static final String GRAMATICA = "Gramatica";
	public static final String JERGA = "Jerga";
	public static final String OTROSD = "OtrosD";
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	//@Column(unique=true)
	@Column(name = "nombre_categoria")
	private String nombre_categoria;

	@OneToMany(mappedBy="nombre_categoria")
	private Collection<Post> Categorias;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	public Categoria(String nombre_categoria) {
		super();
		this.nombre_categoria = nombre_categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCategoria() {
		return nombre_categoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombre_categoria = nombreCategoria;
	}

	public Collection<Post> getCategoria() {
		return Categorias;
	}

	public void setCategoria(Collection<Post> categorias) {
		Categorias = categorias;
	}
}
