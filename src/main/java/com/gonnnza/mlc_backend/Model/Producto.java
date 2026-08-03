/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonManagedReference
 *  jakarta.persistence.CascadeType
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.OneToMany
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=100)
    private String nombre;
    @Column(columnDefinition="TEXT")
    private String descripcion;
    @Column(name="fecha_de_agregado")
    private LocalDate fechaDeAgregado;
    private Integer stock;
    @Column(name="precio_lista")
    private BigDecimal precioLista;
    @Column(name="precio_final")
    private BigDecimal precioFinal;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;
    private Boolean importado;
    @JsonManagedReference
    @OneToMany(mappedBy="producto", cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<Imagen> imagenes;

    public void setImagenes(List<Imagen> nuevasImagenes) {
        if (this.imagenes == null) {
            this.imagenes = new ArrayList<Imagen>();
        } else {
            this.imagenes.clear();
        }
        if (nuevasImagenes == null) {
            return;
        }
        for (Imagen img : nuevasImagenes) {
            img.setProducto(this);
        }
        this.imagenes.addAll(nuevasImagenes);
    }

    @Generated
    public Long getId() {
        return this.id;
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
    public LocalDate getFechaDeAgregado() {
        return this.fechaDeAgregado;
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
    public void setId(Long id) {
        this.id = id;
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
    public void setFechaDeAgregado(LocalDate fechaDeAgregado) {
        this.fechaDeAgregado = fechaDeAgregado;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto other = (Producto)o;
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
        LocalDate this$fechaDeAgregado = this.getFechaDeAgregado();
        LocalDate other$fechaDeAgregado = other.getFechaDeAgregado();
        if (this$fechaDeAgregado == null ? other$fechaDeAgregado != null : !((Object)this$fechaDeAgregado).equals(other$fechaDeAgregado)) {
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
        return other instanceof Producto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $stock = this.getStock();
        result = result * 59 + ($stock == null ? 43 : ((Object)$stock).hashCode());
        Boolean $importado = this.getImportado();
        result = result * 59 + ($importado == null ? 43 : ((Object)$importado).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $descripcion = this.getDescripcion();
        result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
        LocalDate $fechaDeAgregado = this.getFechaDeAgregado();
        result = result * 59 + ($fechaDeAgregado == null ? 43 : ((Object)$fechaDeAgregado).hashCode());
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
        return "Producto(id=" + this.getId() + ", nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", fechaDeAgregado=" + String.valueOf(this.getFechaDeAgregado()) + ", stock=" + this.getStock() + ", precioLista=" + String.valueOf(this.getPrecioLista()) + ", precioFinal=" + String.valueOf(this.getPrecioFinal()) + ", categoria=" + String.valueOf(this.getCategoria()) + ", importado=" + this.getImportado() + ", imagenes=" + String.valueOf(this.getImagenes()) + ")";
    }

    @Generated
    public Producto(Long id, String nombre, String descripcion, LocalDate fechaDeAgregado, Integer stock, BigDecimal precioLista, BigDecimal precioFinal, Categoria categoria, Boolean importado, List<Imagen> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDeAgregado = fechaDeAgregado;
        this.stock = stock;
        this.precioLista = precioLista;
        this.precioFinal = precioFinal;
        this.categoria = categoria;
        this.importado = importado;
        this.imagenes = imagenes;
    }

    @Generated
    public Producto() {
    }
}
