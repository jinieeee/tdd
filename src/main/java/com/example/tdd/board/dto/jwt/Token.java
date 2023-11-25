package com.example.tdd.board.dto.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {

    @Builder
    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    private String grandType = "BEARER";
    private String accessToken;
    private String refreshToken;

    public String getToken() {
        return grandType + " " + accessToken;
    }
}
