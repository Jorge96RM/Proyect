package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Usuario;

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
	
	@Override
	public Usuario datosPerfil(String alias) {
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.alias = :userAlias ", Usuario.class);
		query.setParameter("userAlias", alias);
		Usuario result = query.getResultList().get(0);
		//String ID = (String) result [0];
		//String alias = (String) result [1];
		/*String contrasena = (String) result [1];
		String nombre = (String) result [2];
		String sexo = (String) result [3];
		String rolpordefecto = (String) result [4];
		String primerApellido = (String) result [5];
		String segundoApellido = (String) result [6];
		String email = (String) result [7];
		String telefono = (String) result [8];
		
		Usuario u = new Usuario(alias, contrasena, confirmarContrasena, nombre, primerApellido, segundoApellido, telefono, email, sexo, rolPorDefecto);
		for(Object o : result){
			System.out.println(o);
		}*/
		return result;
	}
}
