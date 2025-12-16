package com.telusko.springsecdemo.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

private String secretKey;
    public JwtService(){
        secretKey =generateSecretKey();
    }
    public String generateToken(String username) {
        Map<String, Object> claim=new HashMap<>();

        return Jwts.builder()
                .setClaims(claim)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*3))
                .signWith(getKey(), SignatureAlgorithm.HS384).compact();
    }

    private Key getKey() {

       // String secretKey;
      byte[] keyBytes=  Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
        //return null;
    }

    public String generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA384");
            SecretKey secretKey = keyGen.generateKey();
            return java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("err in generating secret key");
        }
    }
//
//    public String generateToken(String username){
//
//    }
}
