package com.gonnnza.mlc_backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.Usuario;

public interface CarritoRepo extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuario(Usuario usuario);
    
}
