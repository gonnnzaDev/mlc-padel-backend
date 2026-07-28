package com.gonnnza.mlc_backend.Repository;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gonnnza.mlc_backend.Model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
    List<Producto> findAllByCategoria(Categoria categoria);

    //aca hay que hacer el search
}
