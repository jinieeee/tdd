package com.example.tdd.board.repository.users;

import com.example.tdd.board.domain.users.Users;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserCustomRepository {

    Optional<Users> findByUserEmail(String email);
}
