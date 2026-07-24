package com.gonnnza.mlc_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
    List<Producto> findAllByCategoria(String categoria);

    //aca hay que hacer el search
}
