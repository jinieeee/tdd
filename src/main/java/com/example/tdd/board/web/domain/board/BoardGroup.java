package com.example.tdd.board.web.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class BoardGroup {

    @Id
    @GeneratedValue
    private Long groupId;

    private String groupName;

    @OneToMany(mappedBy = "boardGroup")
    private List<GroupJoin> groupJoinList = new ArrayList<>();

    @OneToMany(mappedBy = "boardGroup")
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public BoardGroup(String groupName) {
        this.groupName = groupName;
    }
}
