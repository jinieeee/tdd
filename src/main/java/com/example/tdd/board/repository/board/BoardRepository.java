package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
}
