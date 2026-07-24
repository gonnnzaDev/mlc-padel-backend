package com.gonnnza.mlc_backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class CarritoProducto {
    private Long id;
    private Long producto_id;
    private Integer cantidad;
}
