package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;

public interface PostRepositoryCustom {
	public List<Post> listarPost(Categoria categoria);
	public long contarPost(Categoria categoria);
	public Post ultimoPost(Categoria categoria);
	public Post todosPost(long id);
	public long todosPostUsuario(long id);
	public List<Post> buscadorPost(String filtro);
}