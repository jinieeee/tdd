package com.example.tdd.board.repository.users;

import com.example.tdd.board.domain.users.QUsers;
import com.example.tdd.board.domain.users.Users;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Users> findByUserEmail(String email) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QUsers users = QUsers.users;

        Users findByUserEmail = queryFactory
                .select(users)
                .from(users)
                .where(users.userEmail.eq(email))
                .fetchOne();

        return Optional.ofNullable(findByUserEmail);
    }
}
