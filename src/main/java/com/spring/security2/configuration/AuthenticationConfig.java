package com.spring.security2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig { // 상속받는거는 요즘 안씀 밑에 @Bean등록해서 씀

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()//인증을 ui가 아니라 token으로 받을거라 거부해놓음
                .csrf().disable()//크로사이트라고함...
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/v1/users/login", "/api/v1/users/join").permitAll() // join, login은 할수있게 인증을 안받아도 되게 풀어준다.
                .antMatchers(HttpMethod.POST, "/api/v1/**").authenticated()  // 리뷰는 막아놔 인증을 받아야 쓸수 있게 // **은 모든것을 뜻함
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용하는 경우 씀
                .and()
                //.addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class) //UserNamePasswordAuthenticationFilter적용하기 전에 JWTTokenFilter를 적용 하라는 뜻 입니다.
                // 토큰 필터는 티켓을 산거라고 보면 돼
                .build();
    }
}
