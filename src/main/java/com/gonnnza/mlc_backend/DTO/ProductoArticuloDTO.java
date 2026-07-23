package com.gonnnza.mlc_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// este es el que va en el index / productos
public class ProductoArticuloDTO {
    
    private String fotoPrincipal;
    private String nombre;
    private Double precioLista;
    private Double precioFinal;

}
