package com.gonnnza.mlc_backend.DTO;

/**
 * ProductoEnCarritoDTO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProductoEnCarritoDTO {

    private String nombre;
    private Integer cantidad;
    private Double precio;
}