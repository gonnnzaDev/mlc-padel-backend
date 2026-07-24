package com.gonnnza.mlc_backend.DTO;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Data


public class ActualizarProductoDTO {

    private String nombre;

    private String descripcion;

    @PositiveOrZero(message = "El stock no puede ser un número negativo")
    private Integer stock;

    private Double precioLista;

    private Double precioFinal;
    
    private Categoria categoria;

    private Boolean importado;

    private List<Imagen> imagenes;

}

