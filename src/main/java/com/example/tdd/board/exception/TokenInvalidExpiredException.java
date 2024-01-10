package com.example.tdd.board.exception;

public class TokenInvalidExpiredException extends RuntimeException {
    public TokenInvalidExpiredException() {
    }

    public TokenInvalidExpiredException(String message) {
        super(message);
    }
}
