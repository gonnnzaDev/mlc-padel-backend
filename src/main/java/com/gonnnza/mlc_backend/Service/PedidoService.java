package com.gonnnza.mlc_backend.Service;

import java.util.List;

import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.ItemCarrito;
import com.gonnnza.mlc_backend.Model.ItemPedido;
import com.gonnnza.mlc_backend.Model.Pedido;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.PedidoRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoService {
    private final PedidoRepo repo;
    private final CarritoService carritoService;

    public void realizarPedido(Long usuario_id, PedidoTicket ticket) {

        List<ItemPedido> pedidosList = List.of();
        Carrito carrito = carritoService.buscarCarritoDeUsuario(usuario_id);
        List<ItemCarrito> productosList = carrito.getProductos();

        for (ItemCarrito item : productosList) {

            ItemPedido pedido = new ItemPedido();
            
            pedido.setProductoId(item.getProducto().getId());
            pedido.setProductoNombre(item.getProducto().getNombre());
            pedido.setPrecioUnitario(item.getProducto().getPrecioFinal());
            pedido.setCantidad(item.getCantidad());
            //este lo tengo que llamar desde el ticket
            pedido.setTicket(ticket);

            pedidosList.add(pedido);
        }

        carritoService.vaciarCarrito(usuario_id);

        repo.saveAll(pedidosList);
    }
}
