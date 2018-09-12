package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public boolean usuarioOK(String usuario, String contrasena) {
		Query query = entityManager.createNativeQuery("SELECT * FROM proyecto.usuario as u " +
				"WHERE u.alias = ? AND u.contrasena = ?");
		query.setParameter(1, usuario);
		query.setParameter(2, contrasena);
		
		//List<Integer> resultado = 
		//int resultadoInt = resultado.get(0);
		return (query.getResultList().size()==1);
	}
	
}
