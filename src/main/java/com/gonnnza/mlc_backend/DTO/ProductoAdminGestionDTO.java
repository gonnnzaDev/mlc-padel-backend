/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.DTO;

import lombok.Generated;

public class ProductoAdminGestionDTO {
    private String nombre;
    private Integer stock;
    private Long id;

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public Integer getStock() {
        return this.stock;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public ProductoAdminGestionDTO() {
    }

    @Generated
    public ProductoAdminGestionDTO(String nombre, Integer stock, Long id) {
        this.nombre = nombre;
        this.stock = stock;
        this.id = id;
    }
}
