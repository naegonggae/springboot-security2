package com.spring.security2.service;

import com.spring.security2.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${jwt.secret}")// yml에 jwt-secret에 적어놔.
    private String secretKey; // Key 가공하는 과정
    private Long expiredTime = 1000 * 60 * 60l;

    public String login(String userName, String password) {
        // 인증과정 생략
        return JwtUtil.createJwt(userName, secretKey, expiredTime);
        // return JwtUtil.createJwt(userName, secretKey, expiredTime); 이렇게하면 userName이 빈칸이야
        // secretKey자리에 secretKey를 바로 쓸수 있지만 보안문제때문에 가공하는 과정을 거친다.
    }
}
