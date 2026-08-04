package com.gonnnza.mlc_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gonnnza.mlc_backend.Model.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Generated;

@Entity
@Table(name="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="producto_id")
    private Long productoId;
    @Column(name="producto_nombre")
    private String productoNombre;
    @Column(name="precio_unitario")
    private BigDecimal precioUnitario;
    private Integer cantidad;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    @Generated
    public Pedido() {
    }

    @Generated
    public Pedido(Long id, Long productoId, String productoNombre, BigDecimal precioUnitario, Integer cantidad, Ticket ticket) {
        this.id = id;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.ticket = ticket;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getProductoId() {
        return this.productoId;
    }

    @Generated
    public String getProductoNombre() {
        return this.productoNombre;
    }

    @Generated
    public BigDecimal getPrecioUnitario() {
        return this.precioUnitario;
    }

    @Generated
    public Integer getCantidad() {
        return this.cantidad;
    }

    @Generated
    public Ticket getTicket() {
        return this.ticket;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    @Generated
    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    @Generated
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Generated
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Generated
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$productoId = this.getProductoId();
        Long other$productoId = other.getProductoId();
        if (this$productoId == null ? other$productoId != null : !((Object)this$productoId).equals(other$productoId)) {
            return false;
        }
        Integer this$cantidad = this.getCantidad();
        Integer other$cantidad = other.getCantidad();
        if (this$cantidad == null ? other$cantidad != null : !((Object)this$cantidad).equals(other$cantidad)) {
            return false;
        }
        String this$productoNombre = this.getProductoNombre();
        String other$productoNombre = other.getProductoNombre();
        if (this$productoNombre == null ? other$productoNombre != null : !this$productoNombre.equals(other$productoNombre)) {
            return false;
        }
        BigDecimal this$precioUnitario = this.getPrecioUnitario();
        BigDecimal other$precioUnitario = other.getPrecioUnitario();
        if (this$precioUnitario == null ? other$precioUnitario != null : !((Object)this$precioUnitario).equals(other$precioUnitario)) {
            return false;
        }
        Ticket this$ticket = this.getTicket();
        Ticket other$ticket = other.getTicket();
        return !(this$ticket == null ? other$ticket != null : !((Object)this$ticket).equals(other$ticket));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Pedido;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $productoId = this.getProductoId();
        result = result * 59 + ($productoId == null ? 43 : ((Object)$productoId).hashCode());
        Integer $cantidad = this.getCantidad();
        result = result * 59 + ($cantidad == null ? 43 : ((Object)$cantidad).hashCode());
        String $productoNombre = this.getProductoNombre();
        result = result * 59 + ($productoNombre == null ? 43 : $productoNombre.hashCode());
        BigDecimal $precioUnitario = this.getPrecioUnitario();
        result = result * 59 + ($precioUnitario == null ? 43 : ((Object)$precioUnitario).hashCode());
        Ticket $ticket = this.getTicket();
        result = result * 59 + ($ticket == null ? 43 : ((Object)$ticket).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Pedido(id=" + this.getId() + ", productoId=" + this.getProductoId() + ", productoNombre=" + this.getProductoNombre() + ", precioUnitario=" + String.valueOf(this.getPrecioUnitario()) + ", cantidad=" + this.getCantidad() + ", ticket=" + String.valueOf(this.getTicket()) + ")";
    }
}
