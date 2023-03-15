package com.example.seconttimespring.security;

import com.example.seconttimespring.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private String validityMilliSecond;
    @PostConstruct
    protected void init(){
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, Authentication authentication){

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles",roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityMilliSecond);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,this.secret)
                .compact();


    }

}
