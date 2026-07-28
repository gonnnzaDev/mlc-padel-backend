package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.ProductoCarritoDTO;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Service.CarritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<?> agregarProductoACarrito(@RequestBody ProductoCarritoDTO dto) {
        carritoService.agregarProductoAlCarrito(dto.getProducto(), dto.getCantidad());
        return ResponseEntity.ok().body("Operacion Realizada Con exito");
    }

    @DeleteMapping
    public ResponseEntity<?> sacarProductoDeCarrito(@RequestBody ProductoCarritoDTO dto) {
        carritoService.sacarProductoDeCarrito(dto.getProducto().getId(), dto.getCantidad());
        return ResponseEntity.ok().body("Operacion Realizada Con exito");
    }
}
