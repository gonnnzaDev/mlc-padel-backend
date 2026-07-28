package com.gonnnza.mlc_backend.DTO;

import com.gonnnza.mlc_backend.Model.ItemPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GuardarPedidoTicketDTO {

    private String direccion;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String nota;
    private String metodoPago;
    private BigDecimal precioTotal;
    private List<ItemPedido> items;
}
