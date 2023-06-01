package com.example.tdd.board.config;

import com.example.tdd.board.service.users.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
            .and()
                .oauth2Login()
                .defaultSuccessUrl("/board/boardList")
                .failureUrl("/error")
                .userInfoEndpoint()
                    .userService(customOAuth2UserService);
        return http.build();
    }
}
