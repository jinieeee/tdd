package com.example.tdd.board.web.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionUser {
    private Long id;
    private String name;
    private String email;
    private String token;
    private Role role;
}
