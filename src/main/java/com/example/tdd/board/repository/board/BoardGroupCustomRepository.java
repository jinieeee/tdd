package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.BoardGroup;

import java.util.List;
import java.util.Optional;

public interface BoardGroupCustomRepository {

    Optional<List<BoardGroup>> findByUserId(Long userId);

    Optional<BoardGroup> findByGroupId(Long groupId);
}
