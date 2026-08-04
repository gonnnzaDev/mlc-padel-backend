package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import lombok.Generated;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
implements UserDetailsService {
    private final UsuarioService usuarioService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = this.usuarioService.buscarUsuarioPorEmail(email);
            return User.builder().username(usuario.getEmail()).roles(new String[]{usuario.getRol().name()}).password(usuario.getPassword()).build();
        }
        catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Generated
    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
