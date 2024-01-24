package com.example.tdd.board.web.config;

import com.example.tdd.board.web.properties.TokenProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public TokenProperties tokenProperties() {
        return new TokenProperties();
    }
}
