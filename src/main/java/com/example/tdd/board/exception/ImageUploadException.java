package com.example.tdd.board.exception;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException() {}

    public ImageUploadException(String message) {
        super(message);
    }
}
