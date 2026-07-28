package com.gonnnza.mlc_backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "productos_carrito")
public class ItemCarrito{

    @Id
    @GeneratedValue
    private Long id;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")

    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
}
