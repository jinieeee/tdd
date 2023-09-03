package com.example.tdd.board.config;

import com.example.tdd.board.service.users.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SpringSecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
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
