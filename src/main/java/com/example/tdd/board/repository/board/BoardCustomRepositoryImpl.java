package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.Board;
import com.example.tdd.board.web.domain.board.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<List<Board>> findByGroupId(Long groupId) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoard board = QBoard.board;

        List<Board> findByGroupId = queryFactory
                .select(board)
                .from(board)
                .where(board.boardGroup.groupId.eq(groupId))
                .fetch();

        return Optional.ofNullable(findByGroupId);
    }
}
