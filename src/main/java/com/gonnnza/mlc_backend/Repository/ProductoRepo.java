package com.gonnnza.mlc_backend.Repository;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo
extends JpaRepository<Producto, Long> {
    public List<Producto> findAllByCategoria(Categoria var1);

    public List<Producto> findByNombreContainingIgnoreCase(String var1);
}
