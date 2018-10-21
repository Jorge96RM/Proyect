package com.example.demo.repositories;

import com.example.demo.domain.Post;
import com.example.demo.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>, RolRepositoryCustom{

}