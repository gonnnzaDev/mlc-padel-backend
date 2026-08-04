package com.gonnnza.mlc_backend.Security;

import com.gonnnza.mlc_backend.Security.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter
extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            UserDetails userDetails;
            String token = null;
            String email = null;
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (!"token".equals(c.getName())) continue;
                        token = c.getValue();
                        break;
                    }
                }
            }
            if (token != null) {
                email = this.jwtUtil.obtenerEmail(token);
            }
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null && this.jwtUtil.esValido(token, userDetails = this.userDetailsService.loadUserByUsername(email))) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken((Object)userDetails, null, userDetails.getAuthorities());
                authToken.setDetails((Object)new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication((Authentication)authToken);
            }
            filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
        }
        catch (JwtException | IllegalArgumentException e) {
            response.setStatus(401);
            response.getWriter().write("Token inv\u00e1lido o expirado");
            return;
        }
    }
}
