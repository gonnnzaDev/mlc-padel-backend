/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonBackReference
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.FetchType
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gonnnza.mlc_backend.Model.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name="imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, columnDefinition="TEXT")
    private String url;
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producto_id")
    private Producto producto;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getUrl() {
        return this.url;
    }

    @Generated
    public Producto getProducto() {
        return this.producto;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUrl(String url) {
        this.url = url;
    }

    @Generated
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$url = this.getUrl();
        String other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
            return false;
        }
        Producto this$producto = this.getProducto();
        Producto other$producto = other.getProducto();
        return !(this$producto == null ? other$producto != null : !((Object)this$producto).equals(other$producto));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Imagen;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        Producto $producto = this.getProducto();
        result = result * 59 + ($producto == null ? 43 : ((Object)$producto).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Imagen(id=" + this.getId() + ", url=" + this.getUrl() + ", producto=" + String.valueOf(this.getProducto()) + ")";
    }

    @Generated
    public Imagen(Long id, String url, Producto producto) {
        this.id = id;
        this.url = url;
        this.producto = producto;
    }

    @Generated
    public Imagen() {
    }
}
