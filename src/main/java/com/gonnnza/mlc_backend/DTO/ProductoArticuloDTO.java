package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Imagen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoArticuloDTO {
    
    private Imagen fotoPrincipal;
    private String nombre;
    private BigDecimal precioLista;
    private BigDecimal precioFinal;
    private Long id;

}
