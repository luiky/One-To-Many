package com.example.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.data.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByName (String name);
}
