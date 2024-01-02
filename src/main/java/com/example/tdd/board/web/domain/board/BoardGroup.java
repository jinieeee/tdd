package com.example.tdd.board.web.domain.board;

import com.example.tdd.board.web.domain.CommonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class BoardGroup extends CommonEntity {

    @Id
    @GeneratedValue
    private Long groupId;

    private String groupName;

    private Long userId;

    private String thumbImgUrl;

    @OneToMany(mappedBy = "boardGroup")
    private List<GroupJoin> groupJoinList = new ArrayList<>();

    @OneToMany(mappedBy = "boardGroup")
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public BoardGroup(String groupName, Long userId, String thumbImgUrl) {
        this.groupName = groupName;
        this.userId = userId;
        this.thumbImgUrl = thumbImgUrl;
    }
}
