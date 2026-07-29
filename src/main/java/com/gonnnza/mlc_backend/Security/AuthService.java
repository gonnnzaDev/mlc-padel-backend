package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

/*
    clase para solicitar datos del usuario activo con su token
*/

public class AuthService {
    private final UsuarioService usuarioService;

    public Usuario getUsuarioActivo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.buscarUsuarioPorEmail(auth.getName());
    }

}
