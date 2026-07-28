package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String email = null;

        //esto es lo q viene del fetch
        final String authHeader = request.getHeader("Authorization");

        //verifica si el token llego
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtUtil.obtenerEmail(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // si es valido el token, lo carga
            if (jwtUtil.esValido(token, userDetails)) {

                //objeto de autenticacion
                // (osea lo q nos va a permitir guardar la autenticacion en spring/backend)

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                //esto le agrega detalles del request al objeto
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                //guarda la autenticacion en spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //para que no se cuelguen las request
        filterChain.doFilter(request, response);

    }
}