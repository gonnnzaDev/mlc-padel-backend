/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.PositiveOrZero
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import lombok.Generated;

public class ActualizarProductoDTO {
    private String nombre;
    private String descripcion;
    @PositiveOrZero(message="El stock no puede ser un n\u00famero negativo")
    private @PositiveOrZero(message="El stock no puede ser un n\u00famero negativo") Integer stock;
    private BigDecimal precioLista;
    private BigDecimal precioFinal;
    private Categoria categoria;
    private Boolean importado;
    private List<Imagen> imagenes;

    @Generated
    public ActualizarProductoDTO() {
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public String getDescripcion() {
        return this.descripcion;
    }

    @Generated
    public Integer getStock() {
        return this.stock;
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
    public Categoria getCategoria() {
        return this.categoria;
    }

    @Generated
    public Boolean getImportado() {
        return this.importado;
    }

    @Generated
    public List<Imagen> getImagenes() {
        return this.imagenes;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Generated
    public void setStock(Integer stock) {
        this.stock = stock;
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
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Generated
    public void setImportado(Boolean importado) {
        this.importado = importado;
    }

    @Generated
    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ActualizarProductoDTO)) {
            return false;
        }
        ActualizarProductoDTO other = (ActualizarProductoDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$stock = this.getStock();
        Integer other$stock = other.getStock();
        if (this$stock == null ? other$stock != null : !((Object)this$stock).equals(other$stock)) {
            return false;
        }
        Boolean this$importado = this.getImportado();
        Boolean other$importado = other.getImportado();
        if (this$importado == null ? other$importado != null : !((Object)this$importado).equals(other$importado)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        String this$descripcion = this.getDescripcion();
        String other$descripcion = other.getDescripcion();
        if (this$descripcion == null ? other$descripcion != null : !this$descripcion.equals(other$descripcion)) {
            return false;
        }
        BigDecimal this$precioLista = this.getPrecioLista();
        BigDecimal other$precioLista = other.getPrecioLista();
        if (this$precioLista == null ? other$precioLista != null : !((Object)this$precioLista).equals(other$precioLista)) {
            return false;
        }
        BigDecimal this$precioFinal = this.getPrecioFinal();
        BigDecimal other$precioFinal = other.getPrecioFinal();
        if (this$precioFinal == null ? other$precioFinal != null : !((Object)this$precioFinal).equals(other$precioFinal)) {
            return false;
        }
        Categoria this$categoria = this.getCategoria();
        Categoria other$categoria = other.getCategoria();
        if (this$categoria == null ? other$categoria != null : !((Object)this$categoria).equals(other$categoria)) {
            return false;
        }
        List<Imagen> this$imagenes = this.getImagenes();
        List<Imagen> other$imagenes = other.getImagenes();
        return !(this$imagenes == null ? other$imagenes != null : !((Object)this$imagenes).equals(other$imagenes));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ActualizarProductoDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $stock = this.getStock();
        result = result * 59 + ($stock == null ? 43 : ((Object)$stock).hashCode());
        Boolean $importado = this.getImportado();
        result = result * 59 + ($importado == null ? 43 : ((Object)$importado).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $descripcion = this.getDescripcion();
        result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
        BigDecimal $precioLista = this.getPrecioLista();
        result = result * 59 + ($precioLista == null ? 43 : ((Object)$precioLista).hashCode());
        BigDecimal $precioFinal = this.getPrecioFinal();
        result = result * 59 + ($precioFinal == null ? 43 : ((Object)$precioFinal).hashCode());
        Categoria $categoria = this.getCategoria();
        result = result * 59 + ($categoria == null ? 43 : ((Object)$categoria).hashCode());
        List<Imagen> $imagenes = this.getImagenes();
        result = result * 59 + ($imagenes == null ? 43 : ((Object)$imagenes).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ActualizarProductoDTO(nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", stock=" + this.getStock() + ", precioLista=" + String.valueOf(this.getPrecioLista()) + ", precioFinal=" + String.valueOf(this.getPrecioFinal()) + ", categoria=" + String.valueOf(this.getCategoria()) + ", importado=" + this.getImportado() + ", imagenes=" + String.valueOf(this.getImagenes()) + ")";
    }
}
