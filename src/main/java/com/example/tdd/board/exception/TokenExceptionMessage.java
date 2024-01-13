package com.example.tdd.board.exception;

public enum TokenExceptionMessage implements ExceptionOperation {

    EXPIRED("this token is expired"),
    INVALID_FORM("this token is invalid form"),
    INVALID_SECRET_KEY("this token has invalid secret key");

    private final String message;

    TokenExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public TokenException exception() {
        return new TokenException(this.message);
    }
}
