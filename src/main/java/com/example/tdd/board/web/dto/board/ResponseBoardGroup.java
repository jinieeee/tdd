package com.example.tdd.board.web.dto.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseBoardGroup {

    private Long groupId;

    private String groupName;

    @Builder
    public ResponseBoardGroup(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public static List<ResponseBoardGroup> toResponseBoardGroup(List<BoardGroup> boardGroupList) {
        List<ResponseBoardGroup> response = new LinkedList<>();
        for(BoardGroup boardGroup : boardGroupList) {
            ResponseBoardGroup responseBoardGroup = ResponseBoardGroup.builder()
                    .groupId(boardGroup.getGroupId())
                    .groupName(boardGroup.getGroupName())
                    .build();
            response.add(responseBoardGroup);
        }
        return response;
    }
}
