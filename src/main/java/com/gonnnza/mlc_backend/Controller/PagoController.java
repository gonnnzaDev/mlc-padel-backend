package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.GuardarPedidoTicketDTO;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Service.MpService;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pago")
@AllArgsConstructor
public class PagoController {
    private final TicketService ticketService;
    private final MpService mpService;


    @PostMapping
    public ResponseEntity<?> pagar(@RequestBody GuardarPedidoTicketDTO dto) throws MPException, MPApiException {
        PedidoTicket ticket = ticketService.transformarGuardarPedidoTicketDTO(dto);
        ticketService.generarTicket(ticket);
        String urlPago = mpService.pagar(ticket);
        return ResponseEntity.ok().body(urlPago);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String id) throws MPException, MPApiException {

        //tira 200 si no tiene id de pago o no tiene q ver con el pago
        if (id == null) {
            return ResponseEntity.ok().build();
        }
        if (!"payment".equals(topic)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.ok().body(mpService.procesarWebhook(
                Long.valueOf(id)));
    }

}
