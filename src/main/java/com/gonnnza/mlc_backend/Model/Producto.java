package com.gonnnza.mlc_backend.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_de_agregado")
    private LocalDate fechaDeAgregado;

    private Integer stock;

    @Column(name = "precio_lista")
    private BigDecimal precioLista;

    @Column(name = "precio_final")
    private BigDecimal precioFinal;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Boolean importado;

    @JsonManagedReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;

    // setter para imagenes para jpa no tire errores
    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;

        if (imagenes == null)
            return;

        for (Imagen img : imagenes) {
            img.setProducto(this);
        }

    }
}
