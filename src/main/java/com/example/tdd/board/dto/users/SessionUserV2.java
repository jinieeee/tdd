package com.example.tdd.board.dto.users;

import com.example.tdd.board.domain.users.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionUserV2 {
    private Long id;
    private String name;
    private String email;
    private String token;
}
