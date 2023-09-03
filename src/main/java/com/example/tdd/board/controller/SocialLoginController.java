package com.example.tdd.board.controller;

import com.example.tdd.board.dto.users.SessionUserV2;
import com.example.tdd.board.service.login.SocialLoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SocialLoginController {

    private final SocialLoginService socialLoginService;

    public SocialLoginController(SocialLoginService socialLoginService) {
        this.socialLoginService = socialLoginService;
    }

    @GetMapping("/user/kakao/callback")
    public Long kakaoLogin(@RequestParam String code, HttpServletResponse response) throws Exception {
        // code: 카카오 서버로부터 받은 인가코드
        SessionUserV2 sessionUser = socialLoginService.kakaoLogin(code);
        response.addHeader("Authorization", "Bearer " + sessionUser.getToken());

        return sessionUser.getId();
    }
}
