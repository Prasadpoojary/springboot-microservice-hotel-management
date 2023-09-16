package com.prasad.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
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



    private Key signInKey()
    {
        byte[] secretByte= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(secretByte);
    }

    public Boolean validateToken(String token)
    {
        logger.info("Entry into validateToken method : JWT Token is being extracted for {}",token);
        return getUsername(token)!=null && !getExpiration(token).before(new Date());
    }

    public String getUsername(String token)
    {
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
