package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Mensaje;
import com.example.demo.domain.Usuario;

public interface MensajeRepositoryCustom {
	public List<Mensaje> listarMensajes(Usuario u);
	public Mensaje contenidoMensaje(long id);
	public long contarMensajesNoLeidos(Usuario id);
}
