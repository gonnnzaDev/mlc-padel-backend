package com.gonnnza.mlc_backend.Repository;

import com.gonnnza.mlc_backend.Model.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo
extends JpaRepository<Categoria, Integer> {
    public Optional<Categoria> findByNombre(String var1);
}
