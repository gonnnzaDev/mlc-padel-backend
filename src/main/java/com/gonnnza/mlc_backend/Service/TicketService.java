package com.gonnnza.mlc_backend.Service;

import java.util.List;

import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Repository.TicketRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final PedidoService pedidoService;

    public List<PedidoTicket> listarTickets() {
        return ticketRepo.findAll();
    }

    public List<PedidoTicket> listarTicketsCancelados(String estado) {

        return ticketRepo.findAllByEstado(EstadoEnum.CANCELADO);
    }

    public List<PedidoTicket> listarTicketsConfirmados(String estado) {

        return ticketRepo.findAllByEstado(EstadoEnum.CONFIRMADO);

    }

    public List<PedidoTicket> listarTicketsPendientes(String estado) {

        return ticketRepo.findAllByEstado(EstadoEnum.PENDIENTE);
    }

    public List<PedidoTicket> listarTicketsRechazados(String estado) {

        return ticketRepo.findAllByEstado(EstadoEnum.RECHAZADO);

    }

    public void confirmarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.CONFIRMADO);
    }

    public void rechazarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.RECHAZADO);
    }

    public void pendienteTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.PENDIENTE);
    }

    public void cancelarTicketPedido(Long id) {

        modificarEstadoPedido(id, EstadoEnum.CANCELADO);
    }

    // este se va a usar post pagar
    public void generarTicket(Long usuario_id, Long ticket_id) {

        PedidoTicket ticket = ticketRepo
                .findById(ticket_id)
                .orElseThrow(() -> new NotFoundException("No existe ese ticket"));

        pedidoService.realizarPedido(usuario_id, ticket);

    }

    public void modificarEstadoPedido(Long id, EstadoEnum estado) throws NotFoundException {
        PedidoTicket ticket = ticketRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No existe ese ticket pedido"));

        if (ticket.getEstado().equals(estado))
            return;

        ticket.setEstado(estado);

        ticketRepo.save(ticket);
    }

}
