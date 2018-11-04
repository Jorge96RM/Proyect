package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;

public interface PostRepositoryCustom {
	public List<Post> listarPost(Categoria categoria);
}
