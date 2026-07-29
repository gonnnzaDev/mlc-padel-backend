package com.gonnnza.mlc_backend.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data

/* Para Actualizar en el apartado de admin */

public class ActualizarProductoDTO {

    private String nombre;

    private String descripcion;

    @PositiveOrZero(message = "El stock no puede ser un número negativo")
    private Integer stock;

    private BigDecimal precioLista;

    private BigDecimal precioFinal;

    private Categoria categoria;

    private Boolean importado;

    private List<Imagen> imagenes;

}

