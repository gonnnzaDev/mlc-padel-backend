package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.gonnnza.mlc_backend.Service.MpService;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
@AllArgsConstructor


// Por aca se va a realizar el pedido por parte del usuario  / comprador
// osea la compra con su respectivo pago

public class PagoController {
    private final TicketService ticketService;
    private final MpService mpService;


    // subo el carrio y se genera un link de pago
    @PostMapping
    public ResponseEntity<?> generarUrlDePago(@RequestBody CarritoDesdeFrontDTO dto) throws MPException, MPApiException {
        //Esto es para no tener que convertirlo 2 veces
        Ticket ticket = ticketService.transformarCarritoDesdeFrontDTO(dto);
        //Logica del endpoint
        //Genero ticket -> genera el link de pago -> retorna el link
        ticketService.generarTicket(ticket);

        ticketService.marcarComoPagadoTicketPendiente(ticket.getId());

        String urlPago = mpService.generarUrlDePago(ticket, dto);

        return ResponseEntity.ok().body(urlPago);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> webhook(
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String id) throws MPException, MPApiException {

        //tira 200 si no tiene id de pago o no tiene q ver con el pago
        if (id == null)
            return ResponseEntity.ok().build();

        if (!"payment".equals(topic))
            return ResponseEntity.ok().build();

        return ResponseEntity.ok().body(mpService.procesarWebhook(
                Long.valueOf(id)));
    }

}
