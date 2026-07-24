package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Imagen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoArticuloDTO {
    
    private Imagen fotoPrincipal;
    private String nombre;
    private Double precioLista;
    private Double precioFinal;
    private Long id;

}
