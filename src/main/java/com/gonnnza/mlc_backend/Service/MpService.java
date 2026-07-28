package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Security.AuthService;
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
    private final AuthService auth;

    public MpService(TicketService ticketService, AuthService auth) {
        this.ticketService = ticketService;
        this.auth = auth;
    }

    public String pagar(PedidoTicket ticket)
            throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(tokenMp);
        Carrito carrito = auth.getCarritoDeUsuarioActivo();

        List<PreferenceItemRequest> items = carrito
                .getProductos()
                .stream()
                .map(i ->
                        PreferenceItemRequest.builder()
                                .title(i.getProducto().getNombre())
                                .quantity(i.getCantidad())
                                .currencyId("ARS")
                                .unitPrice(i.getProducto().getPrecioFinal())
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

    public String procesarWebhook(Long pagoId) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(tokenMp);
        PaymentClient client = new PaymentClient();
        Payment payment = client.get(pagoId);

        Long ticketId = Long.valueOf(payment.getExternalReference());

        if (!"approved".equals(payment.getStatus())) {

            ticketService.rechazarTicketPedido(ticketId);
            return "Error al realizar el pago\n";
        }


        ticketService.marcarComoPagadoTicketPedido(ticketId);
        return "Pago exitoso\n";
    }
}
