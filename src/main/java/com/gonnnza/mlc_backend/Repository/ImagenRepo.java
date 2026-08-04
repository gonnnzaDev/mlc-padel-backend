package com.gonnnza.mlc_backend.Repository;

import com.gonnnza.mlc_backend.Model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepo
extends JpaRepository<Imagen, Long> {
}
