package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true).secure(true).path("/").build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("mensaje", "ok"));

        /*
        return ResponseEntity.ok().body(Map.of("token", token));
        * */
    }
}
