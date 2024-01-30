package com.example.tdd.board.service.board;

import com.example.tdd.board.web.domain.board.Board;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시글이 올바르게 생성")
//    @Transactional
    public void createBoardTest() {
        // given
        Board board = Board.builder()
                .title("내멋대로 제목 짓기")
                .contents("예를 들면 꿍디꿍디")
                .username("네모네모")
                .build();
        Long groupId = 1L;

        // when
        Board saveBoard = boardService.createBoard(board, groupId);

        // then
        Assertions.assertThat(saveBoard.getBoardGroup().getGroupId()).isEqualTo(groupId);
        Assertions.assertThat(saveBoard.getTitle()).isEqualTo(board.getTitle());
    }
}