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

    private String thumbImgUrl;

    @Builder
    public ResponseBoardGroup(Long groupId, String groupName, String thumbImgUrl) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.thumbImgUrl = thumbImgUrl;
    }

    public static List<ResponseBoardGroup> toResponseBoardGroup(List<BoardGroup> boardGroupList) {
        List<ResponseBoardGroup> response = new LinkedList<>();
        for(BoardGroup boardGroup : boardGroupList) {
            ResponseBoardGroup responseBoardGroup = ResponseBoardGroup.builder()
                    .groupId(boardGroup.getGroupId())
                    .thumbImgUrl(boardGroup.getThumbImgUrl())
                    .groupName(boardGroup.getGroupName())
                    .build();
            response.add(responseBoardGroup);
        }
        return response;
    }
}
