package com.gonnnza.mlc_backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


// Vista mini o resumen de los productos en el apartado /admin
public class ProductoAdminGestionDTO {

    private String nombre;
    private Integer stock;
    private Long id;

}
