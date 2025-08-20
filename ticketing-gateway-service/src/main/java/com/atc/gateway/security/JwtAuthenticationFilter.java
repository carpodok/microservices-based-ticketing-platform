package com.atc.gateway.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Gateway-level authentication filter.
 * Validates JWT tokens for incoming API requests and performs coarse authorization
 * by blocking unauthenticated access to `/api/**` endpoints, with the exception of
 * authentication endpoints.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims claims = jwtService.parse(token);
                // optionally expose user info downstream
                request.setAttribute("jwtClaims", claims);
            } catch (Exception ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } else if (requiresAuth(request.getRequestURI())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean requiresAuth(String path) {
        return path.startsWith("/api") && !path.startsWith("/api/auth");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // allow actuator and non-api paths without auth
        return request.getRequestURI().startsWith("/actuator");
    }
}

