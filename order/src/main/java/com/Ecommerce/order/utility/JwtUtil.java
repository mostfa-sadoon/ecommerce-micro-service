package com.Ecommerce.order.utility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUtil    {

    public  static  final String secret_key      = "ThisIsASecretKeyForJWTGenerationChangeIt";

    private Key getSigningKey() {
        return  Keys.hmacShaKeyFor(secret_key.getBytes());
    }

    public String extractUserName(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .setAllowedClockSkewSeconds(300) // Allo 5 minute skue
                    .build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
