/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Imagen;
import java.math.BigDecimal;
import lombok.Generated;

public class ProductoArticuloDTO {
    private Imagen fotoPrincipal;
    private String nombre;
    private BigDecimal precioLista;
    private BigDecimal precioFinal;
    private Long id;
    private Integer stock;

    @Generated
    public Imagen getFotoPrincipal() {
        return this.fotoPrincipal;
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public BigDecimal getPrecioLista() {
        return this.precioLista;
    }

    @Generated
    public BigDecimal getPrecioFinal() {
        return this.precioFinal;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Integer getStock() {
        return this.stock;
    }

    @Generated
    public void setFotoPrincipal(Imagen fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setPrecioLista(BigDecimal precioLista) {
        this.precioLista = precioLista;
    }

    @Generated
    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductoArticuloDTO)) {
            return false;
        }
        ProductoArticuloDTO other = (ProductoArticuloDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$stock = this.getStock();
        Integer other$stock = other.getStock();
        if (this$stock == null ? other$stock != null : !((Object)this$stock).equals(other$stock)) {
            return false;
        }
        Imagen this$fotoPrincipal = this.getFotoPrincipal();
        Imagen other$fotoPrincipal = other.getFotoPrincipal();
        if (this$fotoPrincipal == null ? other$fotoPrincipal != null : !((Object)this$fotoPrincipal).equals(other$fotoPrincipal)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        BigDecimal this$precioLista = this.getPrecioLista();
        BigDecimal other$precioLista = other.getPrecioLista();
        if (this$precioLista == null ? other$precioLista != null : !((Object)this$precioLista).equals(other$precioLista)) {
            return false;
        }
        BigDecimal this$precioFinal = this.getPrecioFinal();
        BigDecimal other$precioFinal = other.getPrecioFinal();
        return !(this$precioFinal == null ? other$precioFinal != null : !((Object)this$precioFinal).equals(other$precioFinal));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ProductoArticuloDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $stock = this.getStock();
        result = result * 59 + ($stock == null ? 43 : ((Object)$stock).hashCode());
        Imagen $fotoPrincipal = this.getFotoPrincipal();
        result = result * 59 + ($fotoPrincipal == null ? 43 : ((Object)$fotoPrincipal).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        BigDecimal $precioLista = this.getPrecioLista();
        result = result * 59 + ($precioLista == null ? 43 : ((Object)$precioLista).hashCode());
        BigDecimal $precioFinal = this.getPrecioFinal();
        result = result * 59 + ($precioFinal == null ? 43 : ((Object)$precioFinal).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ProductoArticuloDTO(fotoPrincipal=" + String.valueOf(this.getFotoPrincipal()) + ", nombre=" + this.getNombre() + ", precioLista=" + String.valueOf(this.getPrecioLista()) + ", precioFinal=" + String.valueOf(this.getPrecioFinal()) + ", id=" + this.getId() + ", stock=" + this.getStock() + ")";
    }

    @Generated
    public ProductoArticuloDTO(Imagen fotoPrincipal, String nombre, BigDecimal precioLista, BigDecimal precioFinal, Long id, Integer stock) {
        this.fotoPrincipal = fotoPrincipal;
        this.nombre = nombre;
        this.precioLista = precioLista;
        this.precioFinal = precioFinal;
        this.id = id;
        this.stock = stock;
    }

    @Generated
    public ProductoArticuloDTO() {
    }
}
