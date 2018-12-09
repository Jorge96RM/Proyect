package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;

public class PostRepositoryImpl implements PostRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> listarPost(Categoria categoria) {
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.nombre_categoria = :idCategoria ORDER BY p.id DESC", Post.class);
		query.setParameter("idCategoria", categoria);
		List<Post> result = query.getResultList();
		return result;
	}
	
	@Override
	public long contarPost(Categoria categoria){ 
		Query query = entityManager.createQuery("SELECT count(p) FROM Post p WHERE p.nombre_categoria = :idCategoria");
		query.setParameter("idCategoria", categoria);
		return (long) query.getSingleResult();
	}
	
	@Override
	public Post ultimoPost(Categoria categoria){ 
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.nombre_categoria = :idCategoria ORDER BY p.id DESC", Post.class);
		query.setParameter("idCategoria", categoria);
		query.setMaxResults(1);
		List<Post> result = query.getResultList();
		if(result.size() == 0){
			return null;
		}else{
			return result.get(0);
		}
	}
	
	@Override
	public Post todosPost(long id) {
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.id = :idPost", Post.class);
		query.setParameter("idPost", id);
		query.setMaxResults(1);
		List<Post> result = query.getResultList();
		return result.get(0);
	}
	
	@Override
	public long todosPostUsuario(long id){ 
		Query query = entityManager.createQuery("SELECT count(post_suyo_id) FROM Post p WHERE post_suyo_id = :idUsuario");
		query.setParameter("idUsuario", id);
		return (long) query.getSingleResult();
	}
	
	@Override
	public List<Post> buscadorPost(String filtro) {
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.titulo LIKE :palabra ORDER BY p.id DESC", Post.class);
		query.setParameter("palabra", ("%"+filtro+"%"));
		List<Post> result = query.getResultList();
		return result;
	}
}
