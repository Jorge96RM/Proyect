package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Post;

public class PostRepositoryImpl implements PostRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> listarPost() {
		TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p", Post.class);
		List<Post> result = query.getResultList();
		return result;
	}
	
}
