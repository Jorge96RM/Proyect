package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Categoria;
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
	
	@Override
	public long contarRespuestasDeCadaPost(Post id){ 
		Query query = entityManager.createQuery("SELECT count(r) FROM Respuesta r WHERE r.postRespuesta = :idPost");
		query.setParameter("idPost", id);
		return (long) query.getSingleResult();
	}
	
	@Override
	public long contarRespuestasDeCadaCategoria(Categoria categoria){ 
		Query query = entityManager.createQuery("SELECT count(*) FROM Post p, Respuesta r WHERE r.postRespuesta = p AND p.nombre_categoria = :idCategoria");
		query.setParameter("idCategoria", categoria);
		return (long) query.getSingleResult();
	}
	
	@Override
	public long todasRespuestas(long id){ 
		Query query = entityManager.createQuery("SELECT count(respuesta_suya_id) FROM Respuesta r WHERE respuesta_suya_id = :idUsuario");
		query.setParameter("idUsuario", id);
		return (long) query.getSingleResult();
	}
	
	
	/*Se hace un borrado de las respuestas antes de borrar el post ya que no deja borrar el post directamente por que tiene respuestas asociadas*/
	@Override
	@Transactional
	public void borrarRespuestaPorId(long id){ 
		Query query = entityManager.createQuery("DELETE FROM Respuesta r WHERE post_respuesta_id = :idRespuesta");
		query.setParameter("idRespuesta", id).executeUpdate();
	}
	
	@Override
	public int mejorRespuesta(long id){ 
		Query query = entityManager.createQuery("SELECT MAX(puntos) from Respuesta WHERE respuesta_suya_id = :idUsuario");
		query.setParameter("idUsuario", id);
		return (int) query.getSingleResult();
	}
}
