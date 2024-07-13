package com.example.tdd.board.web.controller.board;

import com.example.tdd.board.service.board.BoardGroupService;
import com.example.tdd.board.service.board.BoardService;
import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.web.dto.board.RequestBoardGroup;
import com.example.tdd.board.web.dto.board.ResponseBoard;
import com.example.tdd.board.web.dto.board.ResponseBoardGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class BoardGroupController {

    private final BoardService boardService;
    private final BoardGroupService boardGroupService;

    @PostMapping("/createGroup")
    public BoardGroup createGroup(RequestBoardGroup boardGroup) {

        log.info("groupName = {}", boardGroup.getGroupName());
        BoardGroup entity = RequestBoardGroup.toBoardGroupEntity(boardGroup);

        return boardGroupService.createGroup(entity);
    }

    @GetMapping("/all")
    public List<ResponseBoardGroup> allBoardGroup() {
        List<BoardGroup> boardGroupList = boardGroupService.allBoardGroup();
        return ResponseBoardGroup.toResponseBoardGroup(boardGroupList);
    }

    @GetMapping("/{groupId}")
    public List<ResponseBoard> boardsByGroupId(@PathVariable("groupId") Long groupId) {
        return ResponseBoard.toResponseBoard(boardService.findByGroupId(groupId));
    }
}
