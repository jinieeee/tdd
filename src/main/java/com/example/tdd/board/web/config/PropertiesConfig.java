package com.example.tdd.board.web.config;

import com.example.tdd.board.web.properties.TokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {TokenProperties.class})
public class PropertiesConfig {
}
