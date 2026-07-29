package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {

        String token = service.login(dto);
        return ResponseEntity.ok().body(token);
    }
}
