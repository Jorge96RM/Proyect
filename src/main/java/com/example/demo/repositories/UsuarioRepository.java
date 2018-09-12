package com.example.demo.repositories;

import com.example.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom{
	
}
