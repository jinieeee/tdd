package com.example.tdd.board.dto.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestBoardGroup {

    private String groupName;

    public static BoardGroup toBoardGroupEntity(RequestBoardGroup boardGroup) {
        return BoardGroup.builder()
                .groupName(boardGroup.groupName)
                .build();
    }
}
