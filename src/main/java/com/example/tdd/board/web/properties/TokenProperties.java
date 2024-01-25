package com.example.tdd.board.web.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public final class TokenProperties {

    private final AccessToken accessToken;

    private final RefreshToken refreshToken;

    @Getter
    @RequiredArgsConstructor
    public static final class AccessToken {
        private final String secretKey;
        private final long expireLength;
    }

    @Getter
    @RequiredArgsConstructor
    public static final class RefreshToken {
        private final String secretKey;
        private final long expireLength;
    }
}
