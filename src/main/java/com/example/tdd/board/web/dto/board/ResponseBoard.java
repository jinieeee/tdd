package com.example.tdd.board.web.dto.board;

import com.example.tdd.board.web.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseBoard {
    private Long boardId;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime sysRegDtime;

    @Builder
    public ResponseBoard(Long boardId, String title, String contents, String username, LocalDateTime sysRegDtime) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.sysRegDtime = sysRegDtime;
    }

    public static List<ResponseBoard> toResponseBoard(List<Board> boards) {
        List<ResponseBoard> response = new LinkedList<>();
        for(Board board : boards) {
            ResponseBoard responseBoard = ResponseBoard.builder()
                    .boardId(board.getBoardId())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .username(board.getUsername())
                    .sysRegDtime(board.getSysRegDtime())
                    .build();
            response.add(responseBoard);
        }
        return response;
    }
}
