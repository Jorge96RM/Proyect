package com.example.demo.repositories;

import com.example.demo.domain.Usuario;

public interface UsuarioRepositoryCustom {
	public boolean usuarioOK(String alias, String contrasena);
	public Usuario datosPerfil(String alias);
	public Usuario usuarioPorId(long id);
}
