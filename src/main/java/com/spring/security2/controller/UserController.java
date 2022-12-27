package com.spring.security2.controller;

import com.spring.security2.domain.dto.LoginRequest;
import com.spring.security2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest dto) { // 로그인할때 userName이 적용이 되는거임
        return ResponseEntity.ok().body(userService.login(dto.getUserName(), "")); // 받았다고 침
    }
}
