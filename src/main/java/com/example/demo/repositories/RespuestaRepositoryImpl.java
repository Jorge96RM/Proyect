package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Post;
import com.example.demo.domain.Respuesta;

public class RespuestaRepositoryImpl implements RespuestaRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	RespuestaRepository respuestaRepository;
	
	@Override
	public List<Respuesta> listarRespuestasDeCadaPost(Post id) {
		TypedQuery<Respuesta> query = entityManager.createQuery("SELECT r FROM Respuesta r WHERE r.postRespuesta = :idPost", Respuesta.class);
		query.setParameter("idPost", id);
		List<Respuesta> result = query.getResultList();
		return result;
	}
	
	@Override
	public Respuesta respuestaPorId(long id) {
		TypedQuery<Respuesta> query = entityManager.createQuery("SELECT r FROM Respuesta r WHERE r.id = :idRespuesta", Respuesta.class);
		query.setParameter("idRespuesta", id);
		query.setMaxResults(1);
		List<Respuesta> result = query.getResultList();
		return result.get(0);
	}
}
