package com.example.demo.repositories;

import com.example.demo.domain.Categoria;

public interface CategoriaRepositoryCustom {
	public Categoria getByName(String name);
	public Categoria postRedacciones();
	public Categoria postCurriculums();
	public Categoria postCartas();
	public Categoria postOtrosC();
	public Categoria postExpresiones();
	public Categoria postGramatica();
	public Categoria postJerga();
	public Categoria postOtrosD();
}