package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.Service.TicketService;
import java.util.Map;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/pedidos"})
public class PedidoController {
    private final TicketService ticketService;

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping(value={"/cancelar/{id}"})
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {
        this.ticketService.cancelarTicketPedido(id);
        return ResponseEntity.ok().body((Object)"Realizado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping(value={"/realizar/{id}"})
    public ResponseEntity<?> realizadoPedido(@PathVariable Long id) {
        this.ticketService.marcarComoRealizado(id);
        return ResponseEntity.ok().body((Object)"Realizado con exito");
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping
    public ResponseEntity<?> verListaDePedidos() {
        return ResponseEntity.ok().body(Map.of("pedidos", this.ticketService.listarTickets()));
    }

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping(value={"/{estado}"})
    public ResponseEntity<?> verListaDePedidos(@PathVariable String estado) {
        return ResponseEntity.ok().body(Map.of("pedidos", this.ticketService.listarTicketsFiltradosPorEstado(estado)));
    }

    @Generated
    public PedidoController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
