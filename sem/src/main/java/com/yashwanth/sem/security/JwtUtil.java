package com.yashwanth.sem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Minimum 32 characters required
    private static final String SECRET =
            "sams_super_secure_secret_key_for_sem_project_2026_system";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Token validity (24 hours)
    private static final long EXPIRATION_TIME = 86400000;

    // ================= GENERATE TOKEN =================

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // ================= EXTRACT USERNAME =================

    public String extractUsername(String token) {

        return getClaims(token).getSubject();
    }

    // ================= EXTRACT ROLE =================

    public String extractRole(String token) {

        return (String) getClaims(token).get("role");
    }

    // ================= VALIDATE TOKEN =================

    public boolean validateToken(String token) {

        return !getClaims(token).getExpiration().before(new Date());
    }

    // ================= PARSE CLAIMS =================

    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}