/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.access.prepost.PreAuthorize
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.Service.CategoriaService;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/categorias"})
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok().body(this.categoriaService.listarCategorias());
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping(value={"/{categoria}"})
    public ResponseEntity<?> agregarCategoria(@PathVariable String categoria) {
        this.categoriaService.agregarCategoria(categoria);
        return ResponseEntity.ok().body((Object)"Realizado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id) {
        this.categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok().body((Object)"Realizado con exito");
    }

    @Generated
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
}
