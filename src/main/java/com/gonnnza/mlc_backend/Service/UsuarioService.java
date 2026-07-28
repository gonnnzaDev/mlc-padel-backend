package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.Config.SecurityConfig;
import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Security.JwtUtil;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.UsuarioRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepo repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public String login(UsuarioLoginDTO dto) {

        String email = dto.getEmail();
        Usuario usuario = buscarUsuarioPorEmail(email);

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword()))
            throw new NotFoundException("Usuario No Encontrado");

        return jwtUtil.generaToken(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repo
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario No encontrado"));

    }

}
