package com.example.tdd.board.controller.board;

import com.example.tdd.board.dto.users.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/boardList")
    public String boardList(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return user.toString();
    }
}
