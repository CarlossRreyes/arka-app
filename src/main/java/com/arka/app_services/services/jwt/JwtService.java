package com.arka.app_services.services.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    
    @Value("${arka.web.app.jwtSecret}")
	private String jwtSecret;
    
	@Value("${arka.web.app.jwtExpirations}")
	private long jwtExpirations;

    public String generateToken( UserDetails userDetails ){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken( Map<String, Object> extraClaims, UserDetails userDetails ){
        return buildToken(extraClaims, userDetails, jwtExpirations);
    }

    public String buildToken( Map<String, Object> extraClaims, UserDetails userDetails, Long expiration ){
        
        return Jwts
            .builder()
            .setClaims( extraClaims )            
            .setSubject( userDetails.getUsername() )            
            .setIssuedAt( new Date( System.currentTimeMillis() ))
            .setExpiration( new Date( System.currentTimeMillis() + expiration ))                
            .signWith( getSignInKey(),  SignatureAlgorithm.HS256 )
            .compact();
    }   

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaims( String token, Function<Claims, T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims( String token ){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();            
    }

    
    public boolean isTokenValid( String token, UserDetails userDetails ){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired( String token ){
        return extractExpiration(token).before( new Date() );
    }

    private Date extractExpiration( String token ){
        return extractClaims(token, Claims::getExpiration);
    }

    public String extractUsername( String token ){
        return extractClaims(token, Claims::getSubject);
    }





}
