package com.example.tdd.board.exception;

public class TokenInvalidFormException extends RuntimeException {
    public TokenInvalidFormException() {
    }

    public TokenInvalidFormException(String message) {
        super(message);
    }
}
