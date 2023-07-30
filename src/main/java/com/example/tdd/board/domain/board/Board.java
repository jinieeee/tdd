package com.example.tdd.board.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
