/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ace.recipe_sharing.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author adyar
 */
@Service
public class JwtProvider {
    
    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
    
    public String generateToken(Authentication auth){
        String jwt = Jwts.builder().issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key).compact();
        
        return jwt;
    }
    
    public String getEmailFromJwtToken(String jwt){
        //Bearer jwt
        jwt = jwt.substring(7);
        Claims claims = (Claims) Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        
        String email = String.valueOf(claims.get("email"));
        
        return email;
        
    }
    
}
