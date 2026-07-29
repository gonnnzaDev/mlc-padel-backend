package com.gonnnza.mlc_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.Service.ProductoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor

public class ProductoController {

    private final ProductoService service;
    //PUBLICOS  ------------------------------------------------------------------------

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarProductoPorId(id));
    }


    @GetMapping
    public ResponseEntity<?> listarProductos() {

        return ResponseEntity.ok().body(service.listarProductos());
    }

    @GetMapping("/articulos/filtrar/{categoria}")
    public ResponseEntity<?> filtrarProductosPorCategoria(@PathVariable String categoria) {

        return ResponseEntity.ok().body(service.listarProductosDeUnaCategoria(categoria));
    }

    //Buscador
    @GetMapping("/articulos/buscar")
    public ResponseEntity<?> buscarProductos(@RequestParam String q) {
        return ResponseEntity.ok().body(service.buscarProductos(q));
    }

    //Endpoint para el /index
    @GetMapping("/articulos")
    public ResponseEntity<?> listarTodosProductosEnArticulos() {
        return ResponseEntity.ok().body(service.listarProductosEnArticulos());
    }

    //ADMIN ------------------------------------------------------------------------

    //Crud para la vista de admin / duenio

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping("/admin")
    public ResponseEntity<?> listarTodosProductosApartadoAdmin() {
        return ResponseEntity.ok().body(service.listarProductosApartadoAdmin());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.ok().body("Eliminado con exito");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping("/{producto_id}/imagen/{imagen_id}")
    public ResponseEntity<?> eliminarFotoDeProducto(
            @PathVariable("producto_id") Long productoId,
            @PathVariable("imagen_id") Long imagenId) {
        service.eliminarFotoDeProducto(productoId, imagenId);
        return ResponseEntity.ok().body("Eliminado con exito");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ActualizarProductoDTO
            dto) {
        service.actualizarProducto(id, dto);
        return ResponseEntity.ok().body("Actualizado con exito");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody AgregarProductoDTO dto) {
        service.guardarProducto(dto);
        return ResponseEntity.ok().body("Guardado con exito");

    }

}
