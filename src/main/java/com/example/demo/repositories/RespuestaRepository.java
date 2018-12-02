package com.example.demo.repositories;

import com.example.demo.domain.Respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long>, RespuestaRepositoryCustom{
	
}
