package com.gonnnza.mlc_backend.DTO;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AgregarProductoDTO {
    private String nombre;
    private String descripcion;
    private Integer stock;
    private Double precioLista;
    private Double precioFinal;
    private Categoria categoria;
    private Boolean importado;
    private List<Imagen> imagenes;

}
