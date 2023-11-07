package com.example.oneToManyBi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.oneToManyBi.entities.Direccion;
import com.example.oneToManyBi.entities.Usuario;

public interface DireccionRepository extends CrudRepository<Direccion, Long> {
	
	List<Direccion> findByUsuario(Usuario u);

}
