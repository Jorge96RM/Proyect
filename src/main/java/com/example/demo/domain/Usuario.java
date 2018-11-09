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
	private String primerApellido;
	private String segundoApellido;
	private String telefono;
	private String email;
	private String sexo;
	@Column(length=5000)
	private String foto;
	
	@OneToMany(mappedBy="PostSuyo")
	private Collection<Post> Post;
	
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	@ManyToOne
	private Rol nombre_rol;
	
	public Usuario(){
		super();
	}
	
	public Usuario(String alias, String contrasena, String nombre, String primerApellido, String segundoApellido, String telefono, String email, String sexo, Rol nombre_rol) {
		super();
		this.alias = alias;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.telefono = telefono;
		this.email = email;
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
		this.contrasena = contrasena;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		if("".equals(foto)){
			this.foto = null;
		}else{
			this.foto = foto;
		}
	}
	

	
}
