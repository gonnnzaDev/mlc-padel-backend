package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.DTO.CarritoDesdeFrontDTO;
import com.gonnnza.mlc_backend.Model.Ticket;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MpService {

    @Value("${mercadopago.access-token}")
    private String tokenMp;
    private final TicketService ticketService;

    public MpService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public String generarUrlDePago(Ticket ticket, CarritoDesdeFrontDTO carrito)
            throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(tokenMp);

        List<PreferenceItemRequest> items = carrito
                .getItems()
                .stream()
                .map(i ->
                        PreferenceItemRequest.builder()
                                .title(i.getProductoNombre())
                                .quantity(i.getCantidad())
                                .currencyId("ARS")
                                .unitPrice(i.getPrecioUnitario())
                                .build()
                ).toList();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .externalReference(ticket.getId().toString())
                .notificationUrl("https://mlcpadelstore.com.ar/pagos/webhook")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getInitPoint();
    }


    //notificacion que manda mp al back post pago si fue exitosa o fracaso
    public String procesarWebhook(Long pagoId)
            throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(tokenMp);

        PaymentClient client = new PaymentClient();
        Payment payment = client.get(pagoId);

        Long ticketId = Long.valueOf(payment.getExternalReference());
        String status = payment.getStatus();

        switch (status) {

            case "approved":
                ticketService.marcarComoPagadoTicket(ticketId);
                return "Pago aprobado";
            case "rejected":
            case "cancelled":
                ticketService.rechazarTicketPedido(ticketId);
                return "Pago rechazado";
            case "pending":
            case "in_process":
                return "Pago pendiente";

            default:
                return "Estado desconocido";
        }
    }
}
