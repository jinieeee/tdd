package com.example.tdd.board.exception;

public class TokenInvalidSecretKeyException extends Exception {
    public TokenInvalidSecretKeyException() {
    }

    public TokenInvalidSecretKeyException(String message) {
        super(message);
    }
}
