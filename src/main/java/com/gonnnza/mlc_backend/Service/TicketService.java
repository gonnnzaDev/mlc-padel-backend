package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Pedido;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.TicketRepo;
import com.gonnnza.mlc_backend.Security.AuthService;
import com.gonnnza.mlc_backend.Service.ProductoService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepo ticketRepo;
    private final AuthService auth;
    private final ProductoService productoService;

    public Ticket buscarTicketPorId(Long id) {
        return (Ticket)this.ticketRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe ese ticket pedido"));
    }

    public List<Ticket> listarTickets() {
        return this.ticketRepo.findAll().stream().filter(a -> a.getEstado() != EstadoEnum.PENDIENTE && a.getEstado() != EstadoEnum.PROCESANDOSE).toList();
    }

    public List<Ticket> listarTodosLosTickets() {
        return this.ticketRepo.findAll();
    }

    public List<Ticket> listarTicketsFiltradosPorEstado(String estado) {
        List<Ticket> lista = estado.equals(EstadoEnum.CANCELADO.toString()) ? this.ticketRepo.findAllByEstado(EstadoEnum.CANCELADO) : (estado.equals(EstadoEnum.RECHAZADO.toString()) ? this.ticketRepo.findAllByEstado(EstadoEnum.RECHAZADO) : (estado.equals(EstadoEnum.PAGADO.toString()) ? this.ticketRepo.findAllByEstado(EstadoEnum.PAGADO) : (estado.equals(EstadoEnum.REALIZADO.toString()) ? this.ticketRepo.findAllByEstado(EstadoEnum.REALIZADO) : this.ticketRepo.findAllByEstado(EstadoEnum.PENDIENTE))));
        return lista;
    }

    public void modificarEstadoPedido(Long id, EstadoEnum estado) throws NotFoundException {
        Ticket ticket = this.buscarTicketPorId(id);
        if (ticket.getEstado().equals((Object)EstadoEnum.RECHAZADO)) {
            throw new BadRequestException("No se puede modificar el estado a un Pedido rechazado");
        }
        if (ticket.getEstado() == EstadoEnum.PAGADO && estado == EstadoEnum.PENDIENTE) {
            throw new BadRequestException("No se puede volver a pendiente un pedido pagado");
        }
        if (estado == null) {
            throw new BadRequestException("El estado No existe");
        }
        if (ticket.getEstado().equals((Object)estado)) {
            return;
        }
        ticket.setEstado(estado);
        this.ticketRepo.save(ticket);
    }

    @Transactional
    public void marcarComoPagadoTicket(Long id) {
        Ticket ticket = this.buscarTicketPorId(id);
        if (ticket.getEstado() == EstadoEnum.PAGADO) {
            return;
        }
        if (ticket.getEstado() != EstadoEnum.PROCESANDOSE && ticket.getEstado() != EstadoEnum.PENDIENTE) {
            throw new BadRequestException("No se puede marcar como pagado un pedido " + String.valueOf((Object)ticket.getEstado()));
        }
        ticket.getPedidos().forEach(p -> this.productoService.restarStock(p.getProductoId(), p.getCantidad()));
        this.modificarEstadoPedido(id, EstadoEnum.PAGADO);
    }

    public void marcarComoPendienteTicket(Long id) {
        Ticket ticket = this.buscarTicketPorId(id);
        if (ticket.getEstado() != EstadoEnum.PROCESANDOSE) {
            return;
        }
        this.modificarEstadoPedido(id, EstadoEnum.PENDIENTE);
    }

    public void rechazarTicketPedido(Long id) {
        Ticket ticket = this.buscarTicketPorId(id);
        if (ticket.getEstado() == EstadoEnum.RECHAZADO) {
            return;
        }
        if (ticket.getEstado() == EstadoEnum.PAGADO || ticket.getEstado() == EstadoEnum.REALIZADO) {
            return;
        }
        this.modificarEstadoPedido(id, EstadoEnum.RECHAZADO);
    }

    public void marcarComoRealizado(Long id) {
        this.modificarEstadoPedido(id, EstadoEnum.REALIZADO);
    }

    @Transactional
    public void cancelarTicketPedido(Long id) {
        Ticket ticket = this.buscarTicketPorId(id);
        if (ticket.getEstado() == EstadoEnum.CANCELADO) {
            return;
        }
        if (ticket.getEstado() == EstadoEnum.REALIZADO) {
            throw new BadRequestException("No se puede cancelar un pedido ya realizado");
        }
        if (ticket.getEstado() == EstadoEnum.PAGADO) {
            ticket.getPedidos().forEach(p -> this.productoService.sumarStock(p.getProductoId(), p.getCantidad()));
        }
        this.modificarEstadoPedido(id, EstadoEnum.CANCELADO);
    }

    public Ticket transformarCarritoDesdeFrontDTO(CarritoDesdeFrontDTO dto) {
        if (dto == null) {
            throw new BadRequestException("El carrito no existe");
        }
        Usuario usuario = this.auth.getUsuarioActivo();
        Ticket ticket = new Ticket();
        ticket.setUsuario(usuario);
        ticket.setNombre(dto.getNombre());
        ticket.setEmail(dto.getEmail());
        ticket.setTelefono(dto.getTelefono());
        ticket.setNota(dto.getNota());
        ticket.setMetodoPago(dto.getMetodoPago());
        ticket.setPreciototal(dto.getPrecioTotal());
        ticket.setReferenciaDePago(this.generarReferenciaDePago());
        ticket.setEstado(EstadoEnum.PROCESANDOSE);
        ticket.setFechaRealizado(LocalDateTime.now());
        List<Pedido> productos = dto.getItems().stream().map(producto -> {
            Producto productoDB = this.productoService.buscarProductoPorId(producto.getProductoId());
            if (productoDB.getStock() < producto.getCantidad()) {
                throw new BadRequestException("No hay stock suficiente para \"" + productoDB.getNombre() + "\" (disponible: " + productoDB.getStock() + ")");
            }
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
        if (ticket == null) {
            throw new BadRequestException("El ticket no existe");
        }
        return (Ticket)this.ticketRepo.save(ticket);
    }

    private String generarReferenciaDePago() {
        return "MLC-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    }

    @Generated
    public TicketService(TicketRepo ticketRepo, AuthService auth, ProductoService productoService) {
        this.ticketRepo = ticketRepo;
        this.auth = auth;
        this.productoService = productoService;
    }
}
