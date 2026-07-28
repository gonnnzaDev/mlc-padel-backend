package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Producto;
import lombok.Data;

@Data

public class ProductoCarritoDTO {
    Producto producto;
    Integer cantidad;
}
