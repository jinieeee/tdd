package com.example.tdd.board.repository.users;

import com.example.tdd.board.domain.users.Users;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class UserRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    public void userRepositoryFindByEmailTest() {
        // given
        Users userA = Users.builder()
                .userName("김테스트")
                .userEmail("test@test.com")
                .build();

        Users saveUser = userRepository.save(userA);

        // when
        Users findUser = userRepository.findByUserEmail(userA.getUserEmail()).get();

        log.debug("saveUser : {}", saveUser.toString());
        log.debug("findUser : {}", findUser.toString());

        // then
        Assertions.assertThat(saveUser).isEqualTo(findUser);
    }
}