package com.example.tdd.board.jwt;

import com.example.tdd.board.exception.TokenInvalidExpiredException;
import com.example.tdd.board.exception.TokenInvalidFormException;
import com.example.tdd.board.exception.TokenInvalidSecretKeyException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    public String createToken(final String payload) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getPayload(final String token) throws TokenInvalidFormException, TokenInvalidSecretKeyException, TokenInvalidExpiredException {
        return tokenToJws(token).getBody().getSubject();
    }

    private Jws<Claims> tokenToJws(final String token) throws TokenInvalidFormException, TokenInvalidSecretKeyException, TokenInvalidExpiredException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (final IllegalArgumentException | MalformedJwtException e) {
            throw new TokenInvalidFormException();
        } catch (final SignatureException e) {
            throw new TokenInvalidSecretKeyException(token);
        } catch(final ExpiredJwtException e) {
            throw new TokenInvalidExpiredException();
        }
    }

    public void validateAbleToken(final String token) throws TokenInvalidFormException, TokenInvalidSecretKeyException, TokenInvalidExpiredException {
        try {
            final Jws<Claims> claims = tokenToJws(token);
            validateExpiredToken(claims);
        } catch (final JwtException e) {
            throw new TokenInvalidSecretKeyException(token);
        }
    }

    private void validateExpiredToken(final Jws<Claims> claims) throws TokenInvalidExpiredException {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw new TokenInvalidExpiredException();
        }
    }
}
