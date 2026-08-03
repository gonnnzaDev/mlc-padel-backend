/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.OneToMany
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gonnnza.mlc_backend.Model.Producto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Generated;

@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy="categoria")
    private Set<Producto> productos;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public Integer getId() {
        return this.id;
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public Set<Producto> getProductos() {
        return this.productos;
    }

    @Generated
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Integer this$id = this.getId();
        Integer other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        Set<Producto> this$productos = this.getProductos();
        Set<Producto> other$productos = other.getProductos();
        return !(this$productos == null ? other$productos != null : !((Object)this$productos).equals(other$productos));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Categoria;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        Set<Producto> $productos = this.getProductos();
        result = result * 59 + ($productos == null ? 43 : ((Object)$productos).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Categoria(id=" + this.getId() + ", nombre=" + this.getNombre() + ", productos=" + String.valueOf(this.getProductos()) + ")";
    }

    @Generated
    public Categoria() {
    }

    @Generated
    public Categoria(Integer id, String nombre, Set<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }
}
