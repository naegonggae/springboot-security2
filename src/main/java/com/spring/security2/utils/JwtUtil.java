package com.spring.security2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static String createJwt(String userName, String secretKey, Long expiredUtilMs) {
        Claims claims = Jwts.claims(); // claims는 내가 담고싶은 자료를 담는 역할
        claims.put("userName", userName); // 일종의 map "userName"에 userName을 받아올거라는 뜻

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredUtilMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
