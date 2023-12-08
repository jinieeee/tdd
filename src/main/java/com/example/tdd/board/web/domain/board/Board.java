package com.example.tdd.board.web.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long boardId;

    private String title;

    private String contents;

    private String username;

    private LocalDateTime sysRegDtime;

    private LocalDateTime sysModDtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private BoardGroup boardGroup;

    @Builder
    public Board(Long boardId, String title, String contents, String username, LocalDateTime sysRegDtime, LocalDateTime sysModDtime) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.sysRegDtime = sysRegDtime;
        this.sysModDtime = sysModDtime;
    }

    public void changeBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
        boardGroup.getBoardList().add(this);
    }
}
