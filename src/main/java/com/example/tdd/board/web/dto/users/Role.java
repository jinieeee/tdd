package com.example.tdd.board.web.dto.users;

public enum Role {
    ROLE_USER("ROLE_USER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
