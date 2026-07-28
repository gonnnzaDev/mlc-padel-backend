package com.gonnnza.mlc_backend.Controller;

import com.gonnnza.mlc_backend.DTO.UsuarioLoginDTO;
import com.gonnnza.mlc_backend.Service.UsuarioService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
        String token = service.login(dto);

        //esto me lo recomendo chatgpt para no tener que mandar el
        //token cada vez y es mas seguro testear

        Cookie cookie = new Cookie("token", token);
        //javascript no lo puede leer
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login exitoso");

        /* Testear sino sacar lo de cokies y poner este return =3
        return ResponseEntity.ok().body(token);
        * */
    }
}
