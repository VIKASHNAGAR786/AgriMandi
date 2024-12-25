package com.example.AgriMandi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "ui64948w"; // Make sure to use a secure secret key

    // Method to generate JWT token
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Method to validate JWT token
    public static boolean validateToken(String token) {
        try {
            // Parse the token and validate it using the old method
            Claims claims = Jwts.parser()  // Use the older parser() method
                    .setSigningKey(SECRET_KEY)  // Set the secret key for validation
                    .parseClaimsJws(token)      // Parse the JWT token and extract claims
                    .getBody();                 // Get the claims from the parsed JWT

            // Validate expiration date
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return false; // Token has expired
            }

            return true; // Token is valid

        } catch (MalformedJwtException | SignatureException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token has expired: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("JWT validation failed: " + e.getMessage());
        }

        return false; // If any exception occurs, the token is invalid
    }
}
