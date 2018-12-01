package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Mensaje;
import com.example.demo.domain.Usuario;

public class MensajeRepositoryImpl implements MensajeRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	MensajeRepository mensajeRepository;

	@Override
	public List<Mensaje> listarMensajes(Usuario u) {
		TypedQuery<Mensaje> query = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.MensajeA = :idMensaje ORDER BY m.id DESC", Mensaje.class);
		query.setParameter("idMensaje", u);
		List<Mensaje> result = query.getResultList();
		return result;
	}
	
	@Override
	public Mensaje contenidoMensaje(long id) {
		TypedQuery<Mensaje> query = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.id = :idMensaje", Mensaje.class);
		query.setParameter("idMensaje", id);
		List<Mensaje> result = query.getResultList();
		return result.get(0);
	}
	
	public long contarMensajesNoLeidos(Usuario id){ 
		Query query = entityManager.createQuery("SELECT count(m) FROM Mensaje m WHERE m.MensajeA = :idUsuario AND m.leido = false");
		query.setParameter("idUsuario", id);
		return (long) query.getSingleResult();
	}
}
