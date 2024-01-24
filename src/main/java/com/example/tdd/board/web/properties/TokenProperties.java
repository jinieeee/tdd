package com.example.tdd.board.web.properties;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class TokenProperties {

    public AccessToken accessToken;

    public RefreshToken refreshToken;

    public TokenProperties() {
        this.accessToken = new AccessToken();
        this.refreshToken = new RefreshToken();
    }

    @Getter
    @Setter
    public class AccessToken {
        public String secretKey;
        public long expireLength;
    }

    @Getter
    @Setter
    public class RefreshToken {
        public String secretKey;
        public long expireLength;
    }
}
