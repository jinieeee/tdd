package com.example.tdd.board.web.controller.board;

import com.example.tdd.board.dto.users.SessionUser;
import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.dto.board.RequestBoardGroup;
import com.example.tdd.board.service.board.BoardGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class BoardGroupController {

    private final HttpSession httpSession;

    private final BoardGroupService boardGroupService;

    @PostMapping("/createGroup")
    public BoardGroup createGroup(RequestBoardGroup boardGroup) {

        log.info("groupName = {}", boardGroup.getGroupName());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        BoardGroup entity = RequestBoardGroup.toBoardGroupEntity(boardGroup);

        return boardGroupService.createBoard(entity, user.getEmail());
    }
}
