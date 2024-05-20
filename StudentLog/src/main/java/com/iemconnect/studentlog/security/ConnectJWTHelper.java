package com.iemconnect.studentlog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConnectJWTHelper {

    //tocken validity in millisecond
    public static final long JWT_TOCKEN_VALIDTY = 5 * 60 * 60;
    private static final String SECRET = "yoursecretkeyisgoinftosecurreintheporta1234dgfjsdgfgsaklfglkdagflksagfklagfksagfkagfklsagfklagf" +
            "hskadgflsgfkgskfgfgakfglflagfagfgfagfagjflgfagflgfgaflgalfalgfagflagflgafgsdgjsdgkfjgka45";

    //retreive username from jet tocken

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET) // Replace with your actual secret key
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Extract the username from the subject claim
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject(); // Extract the username from the token
            return username.equals(userDetails.getUsername());
        } catch (Exception e) {
            // Handle token validation errors (e.g., expired token, invalid signature)
            return false;
        }
    }

    public String generateTocken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateTocken(claims, userDetails.getUsername());
    }

    private String doGenerateTocken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOCKEN_VALIDTY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }


}
