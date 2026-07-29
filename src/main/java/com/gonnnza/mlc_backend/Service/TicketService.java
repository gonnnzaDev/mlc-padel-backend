package com.gonnnza.mlc_backend.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Pedido;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.TicketRepo;

import com.gonnnza.mlc_backend.Security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/*Ticket de los pedidos*/


@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final AuthService auth;
    private final ProductoService productoService;

    //GET-----------------------------------------------------------------------------

    public Ticket buscarTicketPorId(Long id) {
        return ticketRepo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No existe ese ticket pedido")
                );
    }

    public List<Ticket> listarTickets() {
        return ticketRepo.findAll().stream().filter(a -> a.getEstado() != EstadoEnum.PENDIENTE && a.getEstado() != EstadoEnum.PROCESANDOSE).toList();
    }

    public List<Ticket> listarTodosLosTickets() {
        return ticketRepo.findAll();
    }


    public List<Ticket> listarTicketsFiltradosPorEstado(String estado) {
        List<Ticket> lista;

        if (estado.equals(EstadoEnum.CANCELADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.CANCELADO);
        else if (estado.equals(EstadoEnum.RECHAZADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.RECHAZADO);
        else if (estado.equals(EstadoEnum.PAGADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.PAGADO);
        else if (estado.equals(EstadoEnum.REALIZADO.toString()))
            lista = ticketRepo.findAllByEstado(EstadoEnum.REALIZADO);
        else
            lista = ticketRepo.findAllByEstado(EstadoEnum.PENDIENTE);

        return lista;
    }

    //PUT-----------------------------------------------------------------------------
    //Solo que pueda modificar los estados de el ticket

    public void modificarEstadoPedido(Long id, EstadoEnum estado) throws NotFoundException {

        Ticket ticket = buscarTicketPorId(id);

        if (ticket.getEstado().equals(EstadoEnum.RECHAZADO))
            throw new BadRequestException("No se puede modificar el estado a un Pedido rechazado");

        if (ticket.getEstado() == EstadoEnum.PAGADO && estado == EstadoEnum.PENDIENTE) {
            throw new BadRequestException("No se puede volver a pendiente un pedido pagado");
        }

        if (estado == null)
            throw new BadRequestException("El estado No existe");

        if (ticket.getEstado().equals(estado)) return;

        ticket.setEstado(estado);

        ticketRepo.save(ticket);
    }


    public void marcarComoPagadoTicket(Long id) {

        Ticket ticket = buscarTicketPorId(id);

        if (ticket.getEstado() == EstadoEnum.PAGADO) return;

        modificarEstadoPedido(id, EstadoEnum.PAGADO);
    }

    public void marcarComoPendienteTicket(Long id) {

        Ticket ticket = buscarTicketPorId(id);

        if (ticket.getEstado() != EstadoEnum.PROCESANDOSE) return;

        ticket.getPedidos().forEach(p -> {
            productoService.restarStock(
                    p.getProductoId(),
                    p.getCantidad()
            );
        });

        modificarEstadoPedido(id, EstadoEnum.PENDIENTE);
    }


    public void rechazarTicketPedido(Long id) {

        Ticket ticket = buscarTicketPorId(id);

        if (ticket.getEstado() == EstadoEnum.RECHAZADO) return;

        ticket.getPedidos().forEach(p -> {
            productoService.sumarStock(
                    p.getProductoId(),
                    p.getCantidad()
            );
        });

        modificarEstadoPedido(id, EstadoEnum.RECHAZADO);
    }
    // Estos 2 son los que va a poder interactuar el admin - empleado

    public void marcarComoRealizado(Long id) {

        modificarEstadoPedido(id, EstadoEnum.REALIZADO);
    }


    public void cancelarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.CANCELADO);
    }

    //POST------------------------------------------------------------------------------


    public Ticket transformarCarritoDesdeFrontDTO(CarritoDesdeFrontDTO dto) {

        if (dto == null)
            throw new BadRequestException("El carrito no existe");

        Usuario usuario = auth.getUsuarioActivo();
        Ticket ticket = new Ticket();
        ticket.setUsuario(usuario);
        ticket.setDireccion(dto.getDireccion());
        ticket.setCiudad(dto.getCiudad());
        ticket.setProvincia(dto.getProvincia());
        ticket.setCodigoPostal(dto.getCodigoPostal());
        ticket.setNota(dto.getNota());
        ticket.setMetodoPago(dto.getMetodoPago());
        ticket.setPreciototal(dto.getPrecioTotal());
        ticket.setEstado(EstadoEnum.PROCESANDOSE); //<-
        ticket.setFechaRealizado(LocalDateTime.now());
        List<Pedido> productos = dto.getItems().stream().map(producto -> {

            Pedido item = new Pedido();
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

    public Ticket generarTicket(Ticket ticket) {
        if (ticket == null)
            throw new BadRequestException("El ticket no existe");


        return ticketRepo.save(ticket);
    }


}
