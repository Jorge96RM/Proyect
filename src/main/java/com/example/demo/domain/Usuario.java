package com.example.demo.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.domain.Post;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String alias;
	
	private String contrasena;
	private String nombre;
	private String apellidos;
	private String sexo;
	
	@OneToMany(mappedBy="PostSuyo")
	private Collection<Post> Post;
	
	@ManyToOne
	private Rol nombre_rol;
	
	
	public Usuario(String alias, String contrasena, String nombre, String apellidos, String sexo, Rol nombre_rol) {
		super();
		this.alias = alias;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.nombre_rol = nombre_rol;
		nombre_rol.getRol().add(this);
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		contrasena = contrasena;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		apellidos = apellidos;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		sexo = sexo;
	}
	
	public Collection<Post> getPost() {
		return Post;
	}

	public void setPost(Collection<Post> post) {
		Post = post;
	}

	public Rol getNombre_rol() {
		return nombre_rol;
	}

	public void setNombreRol(Rol nombre_rol) {
		this.nombre_rol = nombre_rol;
	}
	

	
}
