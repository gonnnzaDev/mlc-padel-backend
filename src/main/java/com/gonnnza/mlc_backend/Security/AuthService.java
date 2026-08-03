/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.security.authentication.AnonymousAuthenticationToken
 *  org.springframework.security.core.Authentication
 *  org.springframework.security.core.context.SecurityContextHolder
 *  org.springframework.stereotype.Component
 */
package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import lombok.Generated;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private final UsuarioService usuarioService;

    public Usuario getUsuarioActivo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return this.usuarioService.buscarUsuarioPorEmail(auth.getName());
    }

    @Generated
    public AuthService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
