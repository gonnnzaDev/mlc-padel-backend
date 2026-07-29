package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor

//Controller para gestionar los pedidos
public class PedidoController {

    private final TicketService ticketService;


    // ADMIN Y DUENIO ---------------------------------------------------------------------

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {

        ticketService.cancelarTicketPedido(id);
        return ResponseEntity.ok().body("Realizado con exito");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PutMapping("/realizar/{id}")
    public ResponseEntity<?> realizadoPedido(@PathVariable Long id) {

        ticketService.marcarComoRealizado(id);

        return ResponseEntity.ok().body("Realizado con exito");
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping
    public ResponseEntity<?> verListaDePedidos() {

        return ResponseEntity.ok().body(ticketService.listarTickets());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @GetMapping("/{estado}")
    public ResponseEntity<?> verListaDePedidos(@PathVariable String estado) {

        return ResponseEntity.ok().body(ticketService.listarTicketsFiltradosPorEstado(estado));
    }


}
