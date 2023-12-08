package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.web.domain.board.QBoardGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class BoardGroupCustomRepositoryImpl implements BoardGroupCustomRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<List<BoardGroup>> findByUserId(Long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoardGroup boardGroup = QBoardGroup.boardGroup;

        List<BoardGroup> findByUserId = queryFactory
                .select(boardGroup)
                .from(boardGroup)
                .where(boardGroup.userId.eq(userId))
                .fetch();


        return Optional.ofNullable(findByUserId);
    }
}
