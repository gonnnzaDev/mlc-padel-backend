/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.Email
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.NotNull
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Pedido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Generated;

public class CarritoDesdeFrontDTO {
    @NotBlank(message="El nombre es obligatorio")
    private @NotBlank(message="El nombre es obligatorio") String nombre;
    @NotBlank(message="El email es obligatorio")
    @Email(message="El email no es v\u00e1lido")
    private @NotBlank(message="El email es obligatorio") @Email(message="El email no es v\u00e1lido") String email;
    @NotBlank(message="El tel\u00e9fono es obligatorio")
    private @NotBlank(message="El tel\u00e9fono es obligatorio") String telefono;
    private String nota;
    @NotBlank(message="El m\u00e9todo de pago es obligatorio")
    private @NotBlank(message="El m\u00e9todo de pago es obligatorio") String metodoPago;
    @NotNull(message="El precio total es obligatorio")
    private @NotNull(message="El precio total es obligatorio") BigDecimal precioTotal;
    @NotNull(message="Debe haber al menos un producto")
    private @NotNull(message="Debe haber al menos un producto") List<Pedido> items;

    @Generated
    public CarritoDesdeFrontDTO() {
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getTelefono() {
        return this.telefono;
    }

    @Generated
    public String getNota() {
        return this.nota;
    }

    @Generated
    public String getMetodoPago() {
        return this.metodoPago;
    }

    @Generated
    public BigDecimal getPrecioTotal() {
        return this.precioTotal;
    }

    @Generated
    public List<Pedido> getItems() {
        return this.items;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Generated
    public void setNota(String nota) {
        this.nota = nota;
    }

    @Generated
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Generated
    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Generated
    public void setItems(List<Pedido> items) {
        this.items = items;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CarritoDesdeFrontDTO)) {
            return false;
        }
        CarritoDesdeFrontDTO other = (CarritoDesdeFrontDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$telefono = this.getTelefono();
        String other$telefono = other.getTelefono();
        if (this$telefono == null ? other$telefono != null : !this$telefono.equals(other$telefono)) {
            return false;
        }
        String this$nota = this.getNota();
        String other$nota = other.getNota();
        if (this$nota == null ? other$nota != null : !this$nota.equals(other$nota)) {
            return false;
        }
        String this$metodoPago = this.getMetodoPago();
        String other$metodoPago = other.getMetodoPago();
        if (this$metodoPago == null ? other$metodoPago != null : !this$metodoPago.equals(other$metodoPago)) {
            return false;
        }
        BigDecimal this$precioTotal = this.getPrecioTotal();
        BigDecimal other$precioTotal = other.getPrecioTotal();
        if (this$precioTotal == null ? other$precioTotal != null : !((Object)this$precioTotal).equals(other$precioTotal)) {
            return false;
        }
        List<Pedido> this$items = this.getItems();
        List<Pedido> other$items = other.getItems();
        return !(this$items == null ? other$items != null : !((Object)this$items).equals(other$items));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CarritoDesdeFrontDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $telefono = this.getTelefono();
        result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
        String $nota = this.getNota();
        result = result * 59 + ($nota == null ? 43 : $nota.hashCode());
        String $metodoPago = this.getMetodoPago();
        result = result * 59 + ($metodoPago == null ? 43 : $metodoPago.hashCode());
        BigDecimal $precioTotal = this.getPrecioTotal();
        result = result * 59 + ($precioTotal == null ? 43 : ((Object)$precioTotal).hashCode());
        List<Pedido> $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CarritoDesdeFrontDTO(nombre=" + this.getNombre() + ", email=" + this.getEmail() + ", telefono=" + this.getTelefono() + ", nota=" + this.getNota() + ", metodoPago=" + this.getMetodoPago() + ", precioTotal=" + String.valueOf(this.getPrecioTotal()) + ", items=" + String.valueOf(this.getItems()) + ")";
    }
}
