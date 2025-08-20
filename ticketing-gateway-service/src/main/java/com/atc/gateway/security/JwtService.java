package com.atc.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * Simple utility for parsing and validating JWT tokens.
 * The same signing key should be shared across services so that
 * each microservice can re-validate tokens received from the gateway.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Parses the provided JWT token and returns the claims if valid.
     *
     * @param token raw JWT token without the Bearer prefix
     * @return the claims contained in the token
     */
    public Claims parse(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

