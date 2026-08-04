package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value(value="${jwt.key}")
    private String key;

    private Key getKey() {
        return Keys.hmacShaKeyFor((byte[])this.key.getBytes());
    }

    public String generaToken(Usuario usuario) {
        int tiempoDeExpiracionMs = 10800000;
        return Jwts.builder().setSubject(usuario.getEmail()).claim("id", (Object)usuario.getId()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + (long)tiempoDeExpiracionMs)).signWith(this.getKey(), SignatureAlgorithm.HS512).compact();
    }

    public Claims leerTocken(String token) {
        return (Claims)Jwts.parserBuilder().setSigningKey(this.getKey()).build().parseClaimsJws(token).getBody();
    }

    public String obtenerEmail(String token) {
        return this.leerTocken(token).getSubject();
    }

    public boolean estaExpirado(String token) {
        return this.leerTocken(token).getExpiration().before(new Date());
    }

    public boolean esValido(String token, UserDetails userDetails) {
        String email = this.obtenerEmail(token);
        return email.equals(userDetails.getUsername()) && !this.estaExpirado(token);
    }
}
