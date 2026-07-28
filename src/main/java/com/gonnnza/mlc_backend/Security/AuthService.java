package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.PedidoTicket;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.CarritoRepo;
import com.gonnnza.mlc_backend.Repository.TicketRepo;
import com.gonnnza.mlc_backend.Service.CarritoService;
import com.gonnnza.mlc_backend.Service.TicketService;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthService {
    private final UsuarioService usuarioService;
    private final CarritoRepo carritoRepo;
    private final TicketRepo ticketRepo;

    public Usuario getUsuarioActivo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.buscarUsuarioPorEmail(auth.getName());
    }

    public PedidoTicket getTicketDeUsuarioActivo() {

         return ticketRepo.findByUsuario(getUsuarioActivo());
    }

    public Carrito getCarritoDeUsuarioActivo() {
        return carritoRepo.findByUsuario(getUsuarioActivo())
                .orElseThrow(() -> new NotFoundException("Este usuario no tiene carrito"));
    }
}
