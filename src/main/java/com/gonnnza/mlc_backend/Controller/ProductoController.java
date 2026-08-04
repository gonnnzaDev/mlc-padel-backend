package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.Service.ProductoService;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/productos"})
public class ProductoController {
    private final ProductoService service;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body((Object)this.service.buscarProductoPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok().body(this.service.listarProductos());
    }

    @GetMapping(value={"/articulos/filtrar/{categoria}"})
    public ResponseEntity<?> filtrarProductosPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok().body(this.service.listarProductosDeUnaCategoria(categoria));
    }

    @GetMapping(value={"/articulos/buscar"})
    public ResponseEntity<?> buscarProductos(@RequestParam String q) {
        return ResponseEntity.ok().body(this.service.buscarProductos(q));
    }

    @GetMapping(value={"/articulos"})
    public ResponseEntity<?> listarTodosProductosEnArticulos() {
        return ResponseEntity.ok().body(this.service.listarProductosEnArticulos());
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping(value={"/admin"})
    public ResponseEntity<?> listarTodosProductosApartadoAdmin() {
        return ResponseEntity.ok().body(this.service.listarProductosApartadoAdmin());
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable Long id) {
        this.service.eliminarProducto(id);
        return ResponseEntity.ok().body((Object)"Eliminado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @DeleteMapping(value={"/{producto_id}/imagen/{imagen_id}"})
    public ResponseEntity<?> eliminarFotoDeProducto(@PathVariable(value="producto_id") Long productoId, @PathVariable(value="imagen_id") Long imagenId) {
        this.service.eliminarFotoDeProducto(productoId, imagenId);
        return ResponseEntity.ok().body((Object)"Eliminado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping(value={"/{id}"})
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ActualizarProductoDTO dto) {
        this.service.actualizarProducto(id, dto);
        return ResponseEntity.ok().body((Object)"Actualizado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody AgregarProductoDTO dto) {
        this.service.guardarProducto(dto);
        return ResponseEntity.ok().body((Object)"Guardado con exito");
    }

    @Generated
    public ProductoController(ProductoService service) {
        this.service = service;
    }
}
