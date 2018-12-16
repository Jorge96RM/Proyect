package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;
import com.example.demo.domain.Respuesta;

public interface RespuestaRepositoryCustom {
	public List<Respuesta> listarRespuestasDeCadaPost(Post id);
	public Respuesta respuestaPorId(long id);
	public long contarRespuestasDeCadaPost(Post id);
	public long contarRespuestasDeCadaCategoria(Categoria categoria);
	public long todasRespuestas(long id);
	public void borrarRespuestaPorId(long id);
	public int mejorRespuesta(long id);
}
