package com.example.tdd.board.jwt;

import com.example.tdd.board.exception.TokenExceptionMessage;
import com.example.tdd.board.web.dto.jwt.JwtUserDetails;
import com.example.tdd.board.web.dto.users.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenProvider {
    private final SecretKey key;
    private final long validityInMilliseconds;

    // 생성자 주입 방식
    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                            @Value("${security.jwt.token.expire-length}") long validityInMilliseconds) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String generateToken(final String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("ROLE", role);

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(final String token) {
        return tokenToJws(token).getBody().getSubject();
    }

    private Jws<Claims> tokenToJws(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (final IllegalArgumentException | MalformedJwtException e) {
            throw TokenExceptionMessage.INVALID_FORM.exception();
        } catch (final SignatureException e) {
            throw TokenExceptionMessage.INVALID_SECRET_KEY.exception();
        } catch(final ExpiredJwtException e) {
            throw TokenExceptionMessage.EXPIRED.exception();
        }
    }

    public Boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = tokenToJws(token);
            validateExpiredToken(claims);
            return true;
        } catch (final JwtException e) {
            throw TokenExceptionMessage.INVALID_SECRET_KEY.exception();
        }
    }

    private void validateExpiredToken(final Jws<Claims> claims) {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw TokenExceptionMessage.EXPIRED.exception();
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = this.tokenToJws(token).getBody();

        UserDetails userDetails = JwtUserDetails.builder()
                .username(claims.getSubject())
                .password("")
                .role(Role.ROLE_USER)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
