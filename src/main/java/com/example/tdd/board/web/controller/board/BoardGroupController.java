package com.example.tdd.board.web.controller.board;

import com.example.tdd.board.web.dto.board.ResponseBoardGroup;
import com.example.tdd.board.web.dto.users.SessionUser;
import com.example.tdd.board.web.domain.board.BoardGroup;
import com.example.tdd.board.web.dto.board.RequestBoardGroup;
import com.example.tdd.board.service.board.BoardGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/all")
    public List<ResponseBoardGroup> allBoardGroup() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<BoardGroup> boardGroupList = boardGroupService.allBoardGroup();
        return ResponseBoardGroup.toResponseBoardGroup(boardGroupList);
    }
}
