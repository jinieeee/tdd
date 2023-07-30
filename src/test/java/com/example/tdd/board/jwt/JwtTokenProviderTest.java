package com.example.tdd.board.jwt;

import com.example.tdd.board.exception.TokenInvalidExpiredException;
import com.example.tdd.board.exception.TokenInvalidFormException;
import com.example.tdd.board.exception.TokenInvalidSecretKeyException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final JwtTokenProvider invalidSecretKeyJwtTokenProvider = new JwtTokenProvider("invalidTokenSecretKeyForJwtTokenProviderTest", 8640000L);

    @Test
    @DisplayName("토큰이 올바르게 생성")
    void createToken() {
        final String payload = "abc@abc.com";
        final String token = jwtTokenProvider.createToken(payload);
        Assertions.assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("올바른 토큰 정보로 payload 조회")
    void getPayloadByValidToken() throws TokenInvalidFormException, TokenInvalidSecretKeyException, TokenInvalidExpiredException {
        final String payload = "abc@abc.com";
        final String token = jwtTokenProvider.createToken(payload);
        Assertions.assertThat(jwtTokenProvider.getPayload(token)).isEqualTo(payload);
    }

    @Test
    @DisplayName("유효하지 않은 토큰 형식의 토큰으로 payload 조회하는 경우 예외 발생")
    void getPayloadByInvalidToken() {
        Assertions.assertThatExceptionOfType(TokenInvalidFormException.class).isThrownBy(() -> jwtTokenProvider.getPayload(null));
    }

    @Test
    @DisplayName("만료된 토큰으로 payload 조회하는 경우 예외 발생")
    void getPayloadByExpiredToken() {
        final String expiredToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setSubject("abc@abc.com")
                .setExpiration(new Date((new Date()).getTime() - 1))
                .compact();

        Assertions.assertThatExceptionOfType(TokenInvalidExpiredException.class).isThrownBy(() -> jwtTokenProvider.getPayload(expiredToken));
    }

    @Test
    @DisplayName("setcretKey가 잘못된 토큰 정보로 payload 조회하는 경우 예외 발생")
    void getPayloadByWrongSecretKeyToken() {
        final String invalidSecretToken = invalidSecretKeyJwtTokenProvider.createToken("abc@abc.com");

        Assertions.assertThatExceptionOfType(TokenInvalidSecretKeyException.class).isThrownBy(() -> jwtTokenProvider.getPayload(invalidSecretToken));
    }
}