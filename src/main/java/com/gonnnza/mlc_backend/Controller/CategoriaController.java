package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.Service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


@AllArgsConstructor

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok().body(categoriaService.listarCategorias());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping("/{categoria}")
    public ResponseEntity<?> agregarCategoria(@PathVariable String categoria) {
        categoriaService.agregarCategoria(categoria);
        return ResponseEntity.ok().body("Realizado con exito");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok().body("Realizado con exito");
    }


}