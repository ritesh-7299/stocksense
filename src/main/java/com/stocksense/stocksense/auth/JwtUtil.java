package com.stocksense.stocksense.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private static String SECRET;
    private static long EXPIRATION_TIME;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration_time}") long expirationTime
    ) {
        this.SECRET = secret;
        this.EXPIRATION_TIME = expirationTime;
    }

    private static Key getSignedKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email) {
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractToken(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignedKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return "";
        }
    }
}
