package com.example.demo.repositories;

import com.example.demo.domain.Mensaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>, MensajeRepositoryCustom{
	
}
