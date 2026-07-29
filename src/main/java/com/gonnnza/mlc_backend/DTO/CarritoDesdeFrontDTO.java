package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

// Como no van a haber clientes tiene q
// venir el carrito desde el front que lo
// guardo en localstorage hasta que llega aca
// donde se va a generar un endpoint de pago

@Data
public class CarritoDesdeFrontDTO {

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "La provincia es obligatoria")
    private String provincia;

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotBlank(message = "La nota no puede estar vacía")
    private String nota;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El precio total es obligatorio")
    private BigDecimal precioTotal;

    @NotNull(message = "Debe haber al menos un producto")
    private List<Pedido> items;
}
