/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mercadopago.exceptions.MPApiException
 *  com.mercadopago.exceptions.MPException
 *  jakarta.validation.Valid
 *  lombok.Generated
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.gonnnza.mlc_backend.Service.MpService;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/pagos"})
public class PagoController {
    private final TicketService ticketService;
    private final MpService mpService;

    @PostMapping
    public ResponseEntity<?> generarUrlDePago(@Valid @RequestBody CarritoDesdeFrontDTO dto) throws MPException, MPApiException {
        Ticket ticket = this.ticketService.transformarCarritoDesdeFrontDTO(dto);
        this.ticketService.generarTicket(ticket);
        String urlPago = this.mpService.generarUrlDePago(ticket, dto);
        return ResponseEntity.ok().body((Object)urlPago);
    }

    @PostMapping(value={"/webhook"})
    public ResponseEntity<?> webhook(@RequestParam(required=false) String topic, @RequestParam(required=false) String id) throws MPException, MPApiException {
        if (id == null) {
            return ResponseEntity.ok().build();
        }
        if (!"payment".equals(topic)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().body((Object)this.mpService.procesarWebhook(Long.valueOf(id)));
    }

    @Generated
    public PagoController(TicketService ticketService, MpService mpService) {
        this.ticketService = ticketService;
        this.mpService = mpService;
    }
}
