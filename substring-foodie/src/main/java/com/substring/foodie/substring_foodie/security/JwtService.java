package com.substring.foodie.substring_foodie.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//perform perations with jwt
//craete--
//token se jwt
//validate
@Service
public class JwtService {

    //millis
    private static final long EXPIRATION_TIME=15*60*1000;//for access token->15 minute
    private static final long EXPIRATION_REFRESH_TIME=24*60*60*1000;//for refersh token-> 1 day
    private static final String SECRET = "ssnjkgksrnbrwnsnkbjfknuyggyytfuhvgftytbhrtsjkqvefnjrvsjn";

    private static final String REFRESH_TOKEN_TYPE = "refresh_token";
    private static final String ACCESS_TOKEN_TYPE = "access_token";



    //generate token

    public String generateToken(String username , boolean isAccessToken){

        long expTime = isAccessToken?EXPIRATION_TIME : EXPIRATION_REFRESH_TIME;

        String tokenType= isAccessToken? ACCESS_TOKEN_TYPE : REFRESH_TOKEN_TYPE;

        Map<String , Object> claims = new HashMap<>();
        claims.put("typ", tokenType);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()),SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    //get username from the token

    public String getUsername(String token){
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return username;
    }

    //validate token

    public boolean validateToken(String token){
        if(this.isTokenExpired(token)){
            return false;
        }

        try{
            Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean isTokenExpired(String token){
        Date expiration=Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    public boolean isAccessToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token).getBody();

        String tokenType = (String)claims.get("typ");
        return tokenType.equals(ACCESS_TOKEN_TYPE);
    }

    public boolean isRefreshToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token).getBody();

        String tokenType = (String)claims.get("typ");
        return tokenType.equals(REFRESH_TOKEN_TYPE);

    }

}
