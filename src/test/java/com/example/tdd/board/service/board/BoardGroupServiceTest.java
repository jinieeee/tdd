package com.example.tdd.board.service.board;

import com.example.tdd.board.web.domain.board.Board;
import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.repository.board.BoardGroupRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class BoardGroupServiceTest {

    @Autowired
    private BoardGroupService boardGroupService;

    @Autowired
    private BoardGroupRepository boardGroupRepository;

    @Test
    @DisplayName("게시판 그룹이 올바르게 생성")
    @Transactional // Transactional 어노테이션 없으면 영속성 컨텍스트 종료되어 동일비교 불가
    // @Rollback(false)
    public void createBoardGroupTest() {
        // given
        BoardGroup boardGroupA = BoardGroup.builder()
                .groupName("TEST2")
                .thumbImgUrl("https://cdn.pixabay.com/photo/2023/02/03/05/00/colorful-7764148_1280.jpg")
                .userId(2L)
                .build();

        String email = "aaa@naver.com";

        // when
        BoardGroup saveBoardGroup = boardGroupService.createBoard(boardGroupA, email);

        // then
        BoardGroup findBoardGroup = boardGroupRepository.findById(boardGroupA.getGroupId()).orElseThrow();

        // then
        Assertions.assertThat(saveBoardGroup.getGroupName()).isEqualTo(findBoardGroup.getGroupName());
        Assertions.assertThat(boardGroupA.getGroupJoinList()).isEqualTo(findBoardGroup.getGroupJoinList());
    }

    @Test
    @DisplayName("게시판 그룹 전체가 올바르게 조회")
    public void allBoardGroup() {
        // when
        List<BoardGroup> all = boardGroupService.allBoardGroup();
        Assertions.assertThat(all.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("userId 일치하는 게시판 그룹 전체가 올바르게 조회")
    void findByUserId() {
        // given
        Long userId = 1l;

        // when
        List<BoardGroup> boardGroupList = boardGroupService.findByUserId(userId);

        // then
        for(BoardGroup group : boardGroupList) {
            Assertions.assertThat(group.getUserId()).isEqualTo(userId);
        }
    }
}