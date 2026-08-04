package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.CredentialsException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.UsuarioRepo;
import com.gonnnza.mlc_backend.Security.JwtUtil;
import lombok.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepo repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public String login(UsuarioLoginDTO dto) {
        if (dto == null) {
            throw new BadRequestException("Usuario nulo -> No fue recibido un usuario");
        }
        String email = dto.getEmail();
        Usuario usuario = this.buscarUsuarioPorEmail(email);
        if (!this.passwordEncoder.matches((CharSequence)dto.getPassword(), usuario.getPassword())) {
            throw new CredentialsException("Usuario No Encontrado");
        }
        return this.jwtUtil.generaToken(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return this.repo.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuario No encontrado"));
    }

    @Generated
    public UsuarioService(UsuarioRepo repo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
}
