package com.example.tdd.board.web.dto.board;

import com.example.tdd.board.web.domain.CommonEntity;
import com.example.tdd.board.web.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestBoard extends CommonEntity {
    private String title;
    private String contents;
    private String username;
    private Long groupId;

    public static Board toBoardEntity(RequestBoard board) {
        return Board.builder()
                .title(board.title)
                .contents(board.contents)
                .username(board.username)
                .build();
    }
}
