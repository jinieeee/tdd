package com.example.tdd.board.exception;

public class TokenInvalidFormException extends Exception {
    public TokenInvalidFormException() {
    }

    public TokenInvalidFormException(String message) {
        super(message);
    }
}
