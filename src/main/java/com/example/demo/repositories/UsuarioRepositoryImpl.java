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
		return result;
	}
	
	@Override
	public Usuario usuarioPorId(long id){ 
		Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id = :idUser", Usuario.class);
		query.setParameter("idUser", id);
		query.setMaxResults(1);
		List<Usuario> result = query.getResultList();
		return result.get(0);
	}
	
	@Override
	public List<Usuario> todosUsuarios(){
		Query query = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY puntos DESC", Usuario.class);
		List<Usuario> result = query.getResultList();
		return result;
	}
	
	/*Seleccionar la fila activo de la tabla usuario para poder hacer el baneo de usuario*/
	@Override
	public int esActivo(String alias){ 
		Query query = entityManager.createQuery("SELECT activo FROM Usuario r WHERE alias = :usuarioAlias");
		query.setParameter("usuarioAlias", alias);
		return (int) query.getSingleResult();
	}
}
