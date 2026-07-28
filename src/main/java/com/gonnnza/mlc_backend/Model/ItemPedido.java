    package com.gonnnza.mlc_backend.Model;

    import com.fasterxml.jackson.annotation.JsonIgnore;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;

    @Entity
    @Table(name = "pedidos")

    @NoArgsConstructor
    @AllArgsConstructor
    @Data

    // esto seria pedido / compra realizada

    public class ItemPedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "producto_id")
        private Long productoId;

        @Column(name = "producto_nombre")
        private String productoNombre;

        @Column(name = "precio_unitario")
        private BigDecimal precioUnitario;

        private Integer cantidad;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "pedido_id")
        private PedidoTicket ticket;

    }
