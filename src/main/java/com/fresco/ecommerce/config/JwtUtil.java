package com.fresco.ecommerce.config;

import com.fresco.ecommerce.models.User;
import com.fresco.ecommerce.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.validity}")
    private Integer tokenValidity;

    @Autowired
    private UserRepo repo;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public User getUser(final String token) {
        Claims claims = parseClaims(token);
        Optional<User> optionalUser = repo.findByUsername(claims.getSubject());
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
