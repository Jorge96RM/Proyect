package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;

public class RolRepositoryImpl implements RolRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	RolRepository rolRepository;
	
	@Override
	public Rol getDefaultRol() {
		Query query = entityManager.createNativeQuery("SELECT r.* FROM proyecto.rol as r" +
				"WHERE r.nombre_rol LIKE 'Usuario'", Rol.class);
	        //        "WHERE r.nombre_rol LIKE ?", Rol.class);
		//query.setParameter(1, "%Usuario%");
		Rol rol = new Rol ((String)query.getResultList().get(1));
		//List<Rol> roles = query.getResultList();
		//return (Rol)(roles.get(1));
		return rol;
	}
	
}
