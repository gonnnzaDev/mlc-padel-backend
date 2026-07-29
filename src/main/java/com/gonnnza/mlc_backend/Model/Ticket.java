package com.gonnnza.mlc_backend.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.gonnnza.mlc_backend.Enum.EstadoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "ticket")

//Este seria el ticket de todos los pedidos o productos individuales pedidos
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referencia_de_pago", unique = true)
    private String referenciaDePago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String direccion;

    private String ciudad;

    private String provincia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    private String nota;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    @Column(name = "fecha_realizado")
    private LocalDateTime fechaRealizado;

    @Column(name = "precio_total")
    private BigDecimal preciototal;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
}
