package com.gonnnza.mlc_backend.Repository;

import com.gonnnza.mlc_backend.Model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo
extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String var1);
}
