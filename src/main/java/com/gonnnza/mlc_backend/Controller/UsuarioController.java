package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Security.JwtUtil;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.Generated;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/usuarios"})
public class UsuarioController {
    private final UsuarioService service;
    private final JwtUtil jwtUtil;

    @PostMapping(value={"/login"})
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
        String token = this.service.login(dto);
        ResponseCookie cookie = ResponseCookie.from((String)"token", (String)token).httpOnly(true).secure(true).path("/").build();
        return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Set-Cookie", new String[]{cookie.toString()})).body(Map.of("mensaje", "ok"));
    }

    @PostMapping(value={"/logout"})
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from((String)"token", (String)"").httpOnly(true).secure(true).path("/").maxAge(0L).build();
        return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Set-Cookie", new String[]{cookie.toString()})).body(Map.of("mensaje", "sesion cerrada"));
    }

    @GetMapping(value={"/me"})
    public ResponseEntity<?> me(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (!"token".equals(c.getName())) continue;
                String email = this.jwtUtil.obtenerEmail(c.getValue());
                Usuario usuario = this.service.buscarUsuarioPorEmail(email);
                return ResponseEntity.ok((Object)usuario);
            }
        }
        return ResponseEntity.status((int)401).body((Object)"No autenticado");
    }

    @Generated
    public UsuarioController(UsuarioService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }
}
