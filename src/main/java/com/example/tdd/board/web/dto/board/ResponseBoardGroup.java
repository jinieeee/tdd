package com.example.tdd.board.web.dto.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseBoardGroup {

    private Long groupId;

    private String groupName;

    private String thumbImgUrl;

    private LocalDateTime sysRegDtime;

    @Builder
    public ResponseBoardGroup(Long groupId, String groupName, String thumbImgUrl, LocalDateTime sysRegDtime) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.thumbImgUrl = thumbImgUrl;
        this.sysRegDtime = sysRegDtime;
    }

    public static List<ResponseBoardGroup> toResponseBoardGroup(List<BoardGroup> boardGroupList) {
        List<ResponseBoardGroup> response = new LinkedList<>();
        for(BoardGroup boardGroup : boardGroupList) {
            ResponseBoardGroup responseBoardGroup = ResponseBoardGroup.builder()
                    .groupId(boardGroup.getGroupId())
                    .thumbImgUrl(boardGroup.getThumbImgUrl())
                    .groupName(boardGroup.getGroupName())
                    .sysRegDtime(boardGroup.getSysRegDtime())
                    .build();
            response.add(responseBoardGroup);
        }
        return response;
    }
}
