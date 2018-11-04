package com.example.demo.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.domain.Usuario;

@Entity

@Table(name = "rol", schema="proyecto")
public class Rol {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	//@Column(unique=true)
	@Column(name = "nombre_rol")
	private String nombre_rol;

	@OneToMany(mappedBy="nombre_rol")
	private Collection<Usuario> Roles;
	
	public Rol() {
		// TODO Auto-generated constructor stub
	}
	public Rol(String nombre_rol) {
		super();
		this.nombre_rol = nombre_rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombre_rol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombre_rol = nombreRol;
	}

	public Collection<Usuario> getRol() {
		return Roles;
	}

	public void setRol(Collection<Usuario> roles) {
		Roles = roles;
	}
}
