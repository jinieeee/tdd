package com.example.tdd.board.domain.board;


import com.example.tdd.board.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class GroupJoin {

    @GeneratedValue
    @Id
    private Long joinId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private BoardGroup boardGroup;

    @Column(name = "userId", nullable = false)
    private Long UserId;

    @Builder
    public GroupJoin(BoardGroup boardGroup, Long userId) {
        this.boardGroup = boardGroup;
        UserId = userId;
    }

    public void changeBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
        boardGroup.getGroupJoinList().add(this);
    }
}
