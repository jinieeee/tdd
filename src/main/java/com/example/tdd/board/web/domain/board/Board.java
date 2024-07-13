package com.example.tdd.board.web.domain.board;

import com.example.tdd.board.web.domain.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Board extends CommonEntity {

    @Id
    @GeneratedValue
    private Long boardId;

    private String title;

    private String contents;

    private String username;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private BoardGroup boardGroup;

    @Builder
    public Board(Long boardId, String title, String contents, String username) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    public void changeBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
        boardGroup.getBoardList().add(this);
    }
}
