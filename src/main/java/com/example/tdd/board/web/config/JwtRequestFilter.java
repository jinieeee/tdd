package com.example.tdd.board.web.config;

import com.example.tdd.board.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // 인증 제외할 URL
    private static final List<String> EXCLUDE_URL =
            List.of("/user/kakao/callback");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        String jwtToken = null;
        String payload = null;

        // Bearer token
        jwtToken = token.substring(7); // "Bearer {token}" 형식이므로 문자열 잘라냄
        try {
            payload = jwtTokenProvider.getPayload(jwtToken);

            // token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
            if(payload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if(jwtTokenProvider.validateToken(jwtToken)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(StringUtils.equals(request.getMethod(), HttpMethod.OPTIONS)) {
            return true;
        }
        return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }
}
