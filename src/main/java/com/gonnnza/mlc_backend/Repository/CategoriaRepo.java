package com.gonnnza.mlc_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Model.Categoria;

import java.util.Optional;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria,  Long> {
    Optional<Categoria> findByNombre(String nombre);
}
