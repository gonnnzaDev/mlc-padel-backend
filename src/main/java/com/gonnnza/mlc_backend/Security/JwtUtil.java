package com.gonnnza.mlc_backend.Security;
import org.springframework.beans.factory.annotation.Value;
import com.gonnnza.mlc_backend.Model.Usuario;
import io.jsonwebtoken.*;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;

@Component

public class JwtUtil {

    @Value("${jwt.key}")
    private String key;


    private Key getKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // cuando hace el login aca genera el tocken
    public String generaToken(Usuario usuario) {

        int tiempoDeExpiracionMs = 86400000;

        return Jwts.builder().setSubject(usuario.getEmail())
                .claim("id", usuario.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tiempoDeExpiracionMs))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    //lee el token sirve para los auxiliares en el esValido
    public Claims leerTocken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Para saber de quien es el tocken (aux)
    public String obtenerEmail(String token) {
        return leerTocken(token).getSubject();
    }

    //saber si expiro (aux)
    public boolean estaExpirado(String token) {
        return leerTocken(token).getExpiration().before(new Date());
    }

    //comprueba con los metodos antes creados si es valido
    public boolean esValido(String token, UserDetails userDetails) {
        String email = obtenerEmail(token);
        return email.equals(userDetails.getUsername()) && !estaExpirado(token);
    }


}