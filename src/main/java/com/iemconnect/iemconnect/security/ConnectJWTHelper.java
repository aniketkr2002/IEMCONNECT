package com.iemconnect.iemconnect.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;

@Component
public class ConnectJWTHelper {

    private static final Logger logger = LoggerFactory.getLogger(ConnectJWTHelper.class);

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final String SECRET = "yoursecretkeyisgoinftosecurreintheporta1234dgfjsdgfgsaklfglkdagflksagfklagfksagfkagfklsagfklagf" +
            "hskadgflsgfkgskfgfgakfglflagfagfgfagfagjflgfagflgfgaflgalfalgfagflagflgafgsdgjsdgkfjgka45";

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = extractAllClaims(token);
            String username = claims.getSubject();
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            logger.error("Token validation error: ", e);
            return false;
        }
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey())
                .compact();
    }
}
