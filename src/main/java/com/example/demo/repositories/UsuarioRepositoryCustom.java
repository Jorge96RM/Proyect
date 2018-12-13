package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Usuario;

public interface UsuarioRepositoryCustom {
	public boolean usuarioOK(String alias, String contrasena);
	public Usuario datosPerfil(String alias);
	public Usuario usuarioPorId(long id);
	public List<Usuario> todosUsuarios();
	public int esActivo(String alias);
}
