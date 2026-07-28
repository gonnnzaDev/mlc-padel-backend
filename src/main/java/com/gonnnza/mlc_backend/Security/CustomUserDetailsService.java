package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = usuarioService.buscarUsuarioPorEmail(email);
            return User.builder()
                    .username(usuario.getEmail())
                    .roles(usuario.getRol().name())
                    .password(usuario.getPassword())
                    .build();
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }


    }
}

