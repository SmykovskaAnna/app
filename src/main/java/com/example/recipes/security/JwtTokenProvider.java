package com.example.recipes.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${app.jwt.secret:mysecretkeyforsigningjwttokensinthisrecipeapp}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:86400000}")
    private long jwtExpirationMs;

    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String extractUsername(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(jwtSecret))
                .build()
                .verify(token);
        return jwt.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(jwtSecret))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(jwtSecret))
                    .build()
                    .verify(token);
            return jwt.getExpiresAt().before(new Date());
        } catch (JWTVerificationException e) {
            return true;
        }
    }
}