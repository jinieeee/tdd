package com.example.tdd.board.service.board;

import com.example.tdd.board.domain.board.BoardGroup;
import com.example.tdd.board.repository.board.BoardGroupRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class BoardGroupServiceTest {

    @Autowired
    private BoardGroupService boardGroupService;

    @Autowired
    private BoardGroupRepository boardGroupRepository;

    @Test
    @DisplayName("게시판 그룹이 올바르게 생성")
    @Transactional // Transactional 어노테이션 없으면 영속성 컨텍스트 종료되어 동일비교 불가
    public void createBoardGroupTest() {
        // given
        BoardGroup boardGroupA = BoardGroup.builder()
                .groupName("니콩내콩")
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

}