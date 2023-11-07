package com.example.oneToManyBi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.oneToManyBi.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.direcciones")
    Iterable<Usuario> findAllWithDirecciones();

}
