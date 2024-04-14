package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.Board;

import java.util.List;
import java.util.Optional;


public interface BoardCustomRepository {

    Optional<List<Board>> findByGroupId(Long groupId);
}
