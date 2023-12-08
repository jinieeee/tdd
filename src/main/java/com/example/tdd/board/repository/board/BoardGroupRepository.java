package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardGroupRepository extends JpaRepository<BoardGroup, Long>, BoardGroupCustomRepository {
}
