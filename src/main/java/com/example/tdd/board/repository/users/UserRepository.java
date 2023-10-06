package com.example.tdd.board.repository.users;

import com.example.tdd.board.web.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>, UserCustomRepository {

}
