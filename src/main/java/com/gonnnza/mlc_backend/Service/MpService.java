package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MpService {
    @Value(value="${mercadopago.access-token}")
    private String tokenMp;
    @Value(value="${mercadopago.notification-url}")
    private String notificationUrl;
    private final TicketService ticketService;

    public MpService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public String generarUrlDePago(Ticket ticket, CarritoDesdeFrontDTO carrito) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken((String)this.tokenMp);
        List<PreferenceItemRequest> items = carrito.getItems().stream().map(i -> PreferenceItemRequest.builder().title(i.getProductoNombre()).quantity(i.getCantidad()).currencyId("ARS").unitPrice(i.getPrecioUnitario()).build()).toList();
        PreferenceRequest preferenceRequest = PreferenceRequest.builder().items(items).externalReference(ticket.getId().toString()).notificationUrl(this.notificationUrl).build();
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        return preference.getInitPoint();
    }

    public String procesarWebhook(Long pagoId) throws MPException, MPApiException {
        String status;
        MercadoPagoConfig.setAccessToken((String)this.tokenMp);
        PaymentClient client = new PaymentClient();
        Payment payment = client.get(pagoId);
        Long ticketId = Long.valueOf(payment.getExternalReference());
        switch (status = payment.getStatus()) {
            case "approved": {
                try {
                    this.ticketService.marcarComoPagadoTicket(ticketId);
                    return "Pago aprobado";
                }
                catch (BadRequestException e) {
                    this.ticketService.rechazarTicketPedido(ticketId);
                    return "Pago aprobado pero sin stock suficiente, pedido rechazado";
                }
            }
            case "rejected": 
            case "cancelled": {
                this.ticketService.rechazarTicketPedido(ticketId);
                return "Pago rechazado";
            }
            case "pending": 
            case "in_process": {
                return "Pago pendiente";
            }
        }
        return "Estado desconocido";
    }
}
