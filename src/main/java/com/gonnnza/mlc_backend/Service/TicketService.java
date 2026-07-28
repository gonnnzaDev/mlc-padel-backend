package com.gonnnza.mlc_backend.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.gonnnza.mlc_backend.DTO.GuardarPedidoTicketDTO;
import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.ItemPedido;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.TicketRepo;

import com.gonnnza.mlc_backend.Security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final AuthService auth;
    private final PedidoService pedidoService;

    public List<PedidoTicket> listarTickets() {
        return ticketRepo.findAll();
    }

    public List<PedidoTicket> listarTicketsFiltradosPorEstado(String estado) {
        List<PedidoTicket> lista;

        if (estado.equals(EstadoEnum.CANCELADO.toString())) lista = ticketRepo.findAllByEstado(EstadoEnum.CANCELADO);
        else if (estado.equals(EstadoEnum.CONFIRMADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.CONFIRMADO);
        else if (estado.equals(EstadoEnum.RECHAZADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.RECHAZADO);
        else if (estado.equals(EstadoEnum.PAGADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.PAGADO);
        else lista = ticketRepo.findAllByEstado(EstadoEnum.PENDIENTE);

        return lista;
    }


    public void confirmarTicketPedido(Long id) {
        modificarEstadoPedido(id, EstadoEnum.CONFIRMADO);

    }

    public void pendienteTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.PENDIENTE);
    }

    public void cancelarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.CANCELADO);
    }
    public void marcarComoPagadoTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.PAGADO);
    }

    public void rechazarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.RECHAZADO);
    }

    public PedidoTicket buscarTicketPorid(Long id) {
        return ticketRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe ese ticket"));

    }

    public PedidoTicket buscarPedidoTicketPorUsuario(Usuario usuario) {

        return ticketRepo.findByUsuario(usuario);
    }

    public PedidoTicket transformarGuardarPedidoTicketDTO(GuardarPedidoTicketDTO dto) {
        Usuario usuario = auth.getUsuarioActivo();
        PedidoTicket ticket = new PedidoTicket();
        ticket.setUsuario(usuario);
        ticket.setDireccion(dto.getDireccion());
        ticket.setCiudad(dto.getCiudad());
        ticket.setProvincia(dto.getProvincia());
        ticket.setCodigoPostal(dto.getCodigoPostal());
        ticket.setNota(dto.getNota());
        ticket.setMetodoPago(dto.getMetodoPago());
        ticket.setPreciototal(dto.getPrecioTotal());
        ticket.setEstado(EstadoEnum.PENDIENTE);
        ticket.setFechaRealizado(LocalDateTime.now());
        List<ItemPedido> productos = dto.getItems().stream().map(producto -> {

            ItemPedido item = new ItemPedido();
            item.setProductoId(producto.getProductoId());
            item.setProductoNombre(producto.getProductoNombre());
            item.setCantidad(producto.getCantidad());
            item.setPrecioUnitario(producto.getPrecioUnitario());
            item.setTicket(ticket);

            return item;

        }).toList();
        ticket.setPedidos(productos);

        return ticket;
    }

    //este guarda el ticket post pagar
    public PedidoTicket generarTicket(PedidoTicket ticket) {

        return ticketRepo.save(ticket);
    }

    public void modificarEstadoPedido(Long id, EstadoEnum estado) throws NotFoundException {
        PedidoTicket ticket = ticketRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe ese ticket pedido"));

        if (ticket.getEstado().equals(estado)) return;

        ticket.setEstado(estado);

        ticketRepo.save(ticket);
    }

}
