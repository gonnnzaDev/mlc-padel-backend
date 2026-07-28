package com.gonnnza.mlc_backend.Service;

import java.util.ArrayList;
import java.util.List;

import com.gonnnza.mlc_backend.Model.*;
import com.gonnnza.mlc_backend.Repository.PedidoRepo;

import com.gonnnza.mlc_backend.Security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class PedidoService {
    private final PedidoRepo repo;
    private final CarritoService carritoService;
    private final AuthService authService;

    public void realizarPedido(PedidoTicket ticket) {

        Usuario usuario = ticket.getUsuario();
        List<ItemPedido> pedidosList = new ArrayList<>();
        Carrito carrito = carritoService.buscarCarritoDeUsuario(usuario.getId());
        List<ItemCarrito> productosList = carrito.getProductos();

        for (ItemCarrito item : productosList) {

            ItemPedido pedido = new ItemPedido();
            
            pedido.setProductoId(item.getProducto().getId());
            pedido.setProductoNombre(item.getProducto().getNombre());
            pedido.setPrecioUnitario(item.getProducto().getPrecioFinal());
            pedido.setCantidad(item.getCantidad());
            pedido.setTicket(ticket);

            pedidosList.add(pedido);
        }

        carritoService.vaciarCarrito(usuario.getId());

        repo.saveAll(pedidosList);
    }
}
