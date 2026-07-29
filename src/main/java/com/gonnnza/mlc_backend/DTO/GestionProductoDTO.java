package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class GestionProductoDTO {
    Producto producto;
    Integer cantidad;
}
