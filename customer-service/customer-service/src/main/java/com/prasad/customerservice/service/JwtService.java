package com.prasad.customerservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService
{

    private final String SECRET;

    Logger logger= LoggerFactory.getLogger(JwtService.class);

    public JwtService(@Value("${auth.secret}") String secret)
    {
        SECRET = secret;
    }




    public String generateToken(String email)
    {
        logger.info("Entry into generateToken method");
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);
    }


    private String createToken(Map<String,Object> claims,String email)
    {
        logger.info("Entry into createToken method : JWT Token is being created for {}",email);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(signInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key signInKey()
    {
        byte[] secretByte= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secretByte);
    }

    public Boolean validateToken(String token, UserDetails userDetails)
    {
        return getUsername(token).equals(userDetails.getUsername()) && !getExpiration(token).before(new Date());
    }

    public String getUsername(String token)
    {
        logger.info("Entry into getUsername method : JWT Token is being extracted for {}",token);
        return extractClaims(token, Claims::getSubject);
    }

    private Date getExpiration(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }



    private <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
        Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(signInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
