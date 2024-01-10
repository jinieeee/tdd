package com.example.tdd.board.exception;

public class TokenInvalidSecretKeyException extends RuntimeException {
    public TokenInvalidSecretKeyException() {
    }

    public TokenInvalidSecretKeyException(String message) {
        super(message);
    }
}
