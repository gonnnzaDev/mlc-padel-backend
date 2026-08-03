/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Producto;
import lombok.Generated;

public class GestionProductoDTO {
    Producto producto;
    Integer cantidad;

    @Generated
    public GestionProductoDTO() {
    }

    @Generated
    public GestionProductoDTO(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Generated
    public Producto getProducto() {
        return this.producto;
    }

    @Generated
    public Integer getCantidad() {
        return this.cantidad;
    }

    @Generated
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Generated
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GestionProductoDTO)) {
            return false;
        }
        GestionProductoDTO other = (GestionProductoDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$cantidad = this.getCantidad();
        Integer other$cantidad = other.getCantidad();
        if (this$cantidad == null ? other$cantidad != null : !((Object)this$cantidad).equals(other$cantidad)) {
            return false;
        }
        Producto this$producto = this.getProducto();
        Producto other$producto = other.getProducto();
        return !(this$producto == null ? other$producto != null : !((Object)this$producto).equals(other$producto));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof GestionProductoDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $cantidad = this.getCantidad();
        result = result * 59 + ($cantidad == null ? 43 : ((Object)$cantidad).hashCode());
        Producto $producto = this.getProducto();
        result = result * 59 + ($producto == null ? 43 : ((Object)$producto).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "GestionProductoDTO(producto=" + String.valueOf(this.getProducto()) + ", cantidad=" + this.getCantidad() + ")";
    }
}
