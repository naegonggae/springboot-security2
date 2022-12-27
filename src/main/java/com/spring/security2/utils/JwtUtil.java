package com.spring.security2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static boolean isExpired(String token, String secretKey) { //Expired == 만료된
        // expired가 지금보다 전에 됐으면 true
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date()); // 만료된게(getExpiration()) 지금(new Date())보다 전이냐? 물어보는거
    }

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
