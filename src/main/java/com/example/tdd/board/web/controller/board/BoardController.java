package com.example.tdd.board.web.controller.board;

import com.example.tdd.board.dto.users.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final HttpSession httpSession;


    @GetMapping("/boardList")
    public String boardList(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return user.toString();
    }

    @PostMapping("/createBoard")
    public void createBoard() {

    }
}
