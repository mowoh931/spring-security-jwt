package com.baar.spring_security_jwt.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    protected String secretKey;

    // Generate jwt token
    public String generateToken(UserDetails userDetails) {
        JwtBuilder jwt = Jwts.builder();
        jwt.setSubject(userDetails.getUsername());
        jwt.setIssuedAt(new Date());
        jwt.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
        jwt.signWith(SignatureAlgorithm.HS256, secretKey);
        return jwt.compact();
    }

    // Extract username from jwt token
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    // Validate jwt token
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
