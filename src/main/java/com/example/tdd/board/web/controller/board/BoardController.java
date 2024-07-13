package com.example.tdd.board.web.controller.board;

import com.example.tdd.board.service.board.BoardService;
import com.example.tdd.board.web.domain.board.Board;
import com.example.tdd.board.web.dto.board.RequestBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import org.springframework.web.bind.annotation.*;


@Secured("ROLE_USER")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {


    private final BoardService boardService;

    @PostMapping("/createBoard")
    public Board createBoard(RequestBoard board, Long groupId) {
        Board entity = RequestBoard.toBoardEntity(board);
        return boardService.createBoard(entity, groupId);
    }

    @GetMapping("/{boardId}")
    public Board getBoard(@PathVariable("boardId") Long boardId) {
        return boardService.getBoard(boardId);
    }

    @PatchMapping("/{boardId}")
    public Board updateBoard(@PathVariable("boardId") Long boardId, RequestBoard board) {
        Board entity = RequestBoard.toBoardEntity(boardId, board);
        return boardService.updateBoard(entity);
    }
}
