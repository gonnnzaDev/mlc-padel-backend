package com.gonnnza.mlc_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Model.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria,  Long> {
}
