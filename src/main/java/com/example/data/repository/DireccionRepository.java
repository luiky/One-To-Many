package com.example.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.data.model.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Long> {

}
