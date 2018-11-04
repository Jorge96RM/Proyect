package com.example.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Categoria;

public class CategoriaRepositoryImpl implements CategoriaRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;
    
	@Autowired
	RolRepository categoriaRepository;
	
	@Override
	public Categoria getByName(String name){
		TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nombre_categoria like :categoryName", Categoria.class);
		//TypedQuery<Categoria> query = entityManager.createNativeQuery("SELECT c.* FROM proyecto.categoria as c " +
		//		"WHERE c.nombre_categoria like ?", Categoria.class);
		query.setParameter("categoryName", name);
		List<Categoria> categorias = query.getResultList();
		return (Categoria)(categorias.get(0));
	}
	
	@Override
	public Categoria postRedacciones() {
		return getByName(Categoria.REDACCIONES);
	}
	
	@Override
	public Categoria postCurriculums() {
		return getByName(Categoria.CURRICULUMS);
	}
	
	@Override
	public Categoria postCartas() {
		return getByName(Categoria.CARTAS);
	}
	
	@Override
	public Categoria postOtrosC() {
		return getByName(Categoria.OTROSC);
	}
	
	@Override
	public Categoria postExpresiones() {
		return getByName(Categoria.EXPRESIONES);
	}
	
	@Override
	public Categoria postGramatica() {
		return getByName(Categoria.GRAMATICA);
	}
	
	@Override
	public Categoria postJerga() {
		return getByName(Categoria.JERGA);
	}
	
	@Override
	public Categoria postOtrosD() {
		return getByName(Categoria.OTROSD);
	}
}
