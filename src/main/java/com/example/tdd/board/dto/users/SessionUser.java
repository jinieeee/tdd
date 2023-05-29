package com.example.tdd.board.dto.users;

import com.example.tdd.board.domain.users.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;

    public SessionUser(Users users) {
        this.name = users.getUserName();
        this.email = users.getUserEmail();
    }
}
