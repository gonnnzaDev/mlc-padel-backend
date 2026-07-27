package com.gonnnza.mlc_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.Usuario;

public interface CarritoRepo extends JpaRepository<Carrito, Long> {
    Carrito findByUsuario(Usuario usuario);
    
}
