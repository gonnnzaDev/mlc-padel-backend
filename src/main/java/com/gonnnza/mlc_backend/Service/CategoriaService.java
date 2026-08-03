/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.stereotype.Service
 */
package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Repository.CategoriaRepo;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepo repo;

    public Categoria buscarCategoriaPorNombre(String nombre) {
        return this.repo.findByNombre(nombre).orElseThrow(() -> new NotFoundException("No existe esa categoria"));
    }

    public List<Categoria> listarCategorias() {
        return this.repo.findAll();
    }

    public void agregarCategoria(String nombre) {
        if (nombre == null) {
            throw new BadRequestException("Categoria invalida");
        }
        Categoria categoria = new Categoria(nombre);
        this.repo.save(categoria);
    }

    public void eliminarCategoria(Integer id) {
        this.repo.deleteById(id);
    }

    @Generated
    public CategoriaService(CategoriaRepo repo) {
        this.repo = repo;
    }
}
