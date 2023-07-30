package com.example.tdd.board.repository.board;

import com.example.tdd.board.domain.board.BoardGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGroupRepository extends JpaRepository<BoardGroup, Long> {
}
