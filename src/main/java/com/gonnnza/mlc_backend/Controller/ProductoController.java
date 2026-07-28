package com.gonnnza.mlc_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.Service.ProductoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
@AllArgsConstructor

public class ProductoController {

    private final ProductoService service;
    //PUBLICOS
    @GetMapping
    public ResponseEntity<?> listarTodosLosProductos() {
        return ResponseEntity.ok().body(service.listarProductos());
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<?> listarTodosLosProductosDeUnaCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok().body(service.listarProductosDeUnaCategoria(categoria));
    }
    //ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/articulos")
    public ResponseEntity<?> listarTodosProductosEnArticulos() {
        return ResponseEntity.ok().body(service.listarProductosEnArticulos());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> listarTodosProductosApartadoAdmin() {
        return ResponseEntity.ok().body(service.listarProductosApartadoAdmin());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.ok().body("Eliminado con exito");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ActualizarProductoDTO dto) {
        service.actualizarProducto(id, dto);
        return ResponseEntity.ok().body("Actualizado con exito");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody AgregarProductoDTO dto) {
        service.guardarProducto(dto);
        return ResponseEntity.ok().body("Guardado con exito");

    }

}
