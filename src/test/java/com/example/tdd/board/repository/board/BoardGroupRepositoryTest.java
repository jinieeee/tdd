package com.example.tdd.board.repository.board;

import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.web.domain.board.GroupJoin;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class BoardGroupRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    BoardGroupRepository boardGroupRepository;

    @Autowired
    GroupJoinRepository groupJoinRepository;

    @Test
    public void BoardGroupRepositorySaveTest(){
        // given
        BoardGroup boardGroupA = BoardGroup.builder()
                .groupName("테스트A Group")
                .build();

        GroupJoin groupJoinA = GroupJoin.builder()
                .userId(1L)
                .build();

        GroupJoin groupJoinB = GroupJoin.builder()
                .userId(2L)
                .build();

        groupJoinA.changeBoardGroup(boardGroupA);
        groupJoinB.changeBoardGroup(boardGroupA);

        BoardGroup saveBoardGroup = boardGroupRepository.save(boardGroupA);
        GroupJoin saveGroupJoinA = groupJoinRepository.save(groupJoinA);
        GroupJoin saveGroupJoinB = groupJoinRepository.save(groupJoinB);

        // when
        BoardGroup findBoardGroup = boardGroupRepository.findById(boardGroupA.getGroupId()).orElseThrow();


        // then
        Assertions.assertThat(boardGroupA).isEqualTo(findBoardGroup);
        Assertions.assertThat(2).isEqualTo(findBoardGroup.getGroupJoinList().size());
    }
}