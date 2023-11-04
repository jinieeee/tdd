package com.example.tdd.board.service.login;

import com.example.tdd.board.dto.users.Role;
import com.example.tdd.board.web.domain.users.OAuthAttributes;
import com.example.tdd.board.web.domain.users.Users;
import com.example.tdd.board.dto.users.SessionUser;
import com.example.tdd.board.jwt.JwtTokenProvider;
import com.example.tdd.board.repository.users.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SocialLoginService {



    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String grantType;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public SocialLoginService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public SessionUser kakaoLogin(String code) throws Exception {
        // 1. 인가코드로 Access Token 요청
        String accessToken = getAccessToken(code, "http://localhost:3000/user/kakao/callback");

        // 2. 회원 register or update
        Users user = userRegisterOrUpdate(accessToken);

        // 3. 로그인 JWT 토큰 발행
        String token = generateJwt(user);

        return SessionUser.builder()
                .id(user.getUserId())
                .token(token)
                .email(user.getUserEmail())
                .name(user.getUserName())
                .role(Role.ROLE_USER)
                .build();
    }

    private String getAccessToken(String code, String redirectUri) throws Exception {
        // kakao developers 문서대로 작성

        // HTTP Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType);
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.postForEntity(
                tokenUri,
                kakaoTokenRequest,
                String.class
        );

        // body token
        //<200,{"access_token":"생략","token_type":"bearer","refresh_token":"생략","expires_in":21599,"scope":"account_email profile_nickname","refresh_token_expires_in":5183999}
        String responseBody = response.getBody();
        // JsonParser deprecated -> ObjectMapper 사용
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private Users userRegisterOrUpdate(String accessToken) throws Exception {
        Map<String, Object> response = (Map<String, Object>) getKakaoUserInfo(accessToken);
        OAuthAttributes attributes = OAuthAttributes.ofKakao("nickname", response);

        Users users = saveOrUpdate(attributes);

        return users;
    }

    private Users saveOrUpdate(OAuthAttributes attributes) {
        Users users = userRepository.findByUserEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return userRepository.save(users);
    }

    /**
     *
     * @param accessToken
     * @return
     */
    private Map<String, Object> getKakaoUserInfo(String accessToken) throws Exception {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.postForEntity(
                "https://kapi.kakao.com/v2/user/me",
                kakaoUserInfoRequest,
                String.class
        );

        // "properties":{"nickname":"test"},"kakao_account":{"profile_nickname_needs_agreement":false,"profile":{"nickname":"test"},"has_email":true,"email_needs_agreement":false,"is_email_valid":true,"is_email_verified":true,"email":"test@test.com"}}
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(responseBody, Map.class);
    }

    // JWT 토큰 생성
    private String generateJwt(Users user) {
        String TOKEN_TYPE = "BEARER";

        String token = jwtTokenProvider.generateToken(user.getUserName(), Role.ROLE_USER);

        return TOKEN_TYPE + " " + token;
    }
}
