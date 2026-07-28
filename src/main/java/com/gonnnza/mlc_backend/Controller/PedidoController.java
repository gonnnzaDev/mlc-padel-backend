package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.GuardarPedidoTicketDTO;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Service.MpService;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PedidoController {

    private final TicketService ticketService;




    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/confirmar/{id}")
    public ResponseEntity<?> confirmarPedido(@PathVariable Long id){

        ticketService.confirmarTicketPedido(id);
        return ResponseEntity.ok().body("Realizado con exito");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id){

        ticketService.cancelarTicketPedido(id);
        return ResponseEntity.ok().body("Realizado con exito");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> verListaDePedidos() {

        return ResponseEntity.ok().body(ticketService.listarTickets());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{estado}")
    public ResponseEntity<?> verListaDePedidos(@PathVariable String estado) {

        return ResponseEntity.ok().body(ticketService.listarTicketsFiltradosPorEstado(estado));
    }



}
