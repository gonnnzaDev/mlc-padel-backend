package com.gonnnza.mlc_backend.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;


/* Formulario en admin agregar producto */

@AllArgsConstructor
@Data
public class AgregarProductoDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser un número negativo")
    private Integer stock;

    @NotNull(message = "El precio de lista es obligatorio")
    private BigDecimal precioLista;

    @NotNull(message = "El precio final es obligatorio")
    private BigDecimal precioFinal;
    
    @NotNull(message = "La categoria es obligatoria")
    private Categoria categoria;
    @NotNull(message = "Indica si es importado o no")
    private Boolean importado;

    @NotEmpty(message = "Debe haber al menos una imagen")
    private List<Imagen> imagenes;

}

