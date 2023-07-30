package com.example.tdd.board.exception;

public class TokenInvalidExpiredException extends Exception{
    public TokenInvalidExpiredException() {
    }

    public TokenInvalidExpiredException(String message) {
        super(message);
    }
}
