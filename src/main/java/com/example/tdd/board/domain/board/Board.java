package com.example.tdd.board.domain.board;

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

    public void changeBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
        boardGroup.getBoardList().add(this);
    }
}
