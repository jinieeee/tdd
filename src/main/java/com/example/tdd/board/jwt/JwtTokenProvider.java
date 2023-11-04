package com.example.tdd.board.jwt;

import com.example.tdd.board.dto.jwt.JwtUserDetails;
import com.example.tdd.board.dto.users.Role;
import com.example.tdd.board.exception.TokenInvalidExpiredException;
import com.example.tdd.board.exception.TokenInvalidFormException;
import com.example.tdd.board.exception.TokenInvalidSecretKeyException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

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

    public String getPayload(final String token) throws Exception {
        return tokenToJws(token).getBody().getSubject();
    }

    private Jws<Claims> tokenToJws(final String token) throws Exception {
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
        } catch(Exception e) {
            throw e;
        }
    }

    public Boolean validateAbleToken(final String token) throws Exception {
        try {
            final Jws<Claims> claims = tokenToJws(token);
            validateExpiredToken(claims);
            return true;
        } catch (final JwtException e) {
            throw new TokenInvalidSecretKeyException(token);
        }
    }

    private void validateExpiredToken(final Jws<Claims> claims) throws TokenInvalidExpiredException {
        if (claims.getBody().getExpiration().before(new Date())) {
            throw new TokenInvalidExpiredException();
        }
    }

    public Authentication getAuthentication(String token) throws Exception {
        Claims claims = this.tokenToJws(token).getBody();

        UserDetails userDetails = JwtUserDetails.builder()
                .username(claims.getSubject())
                .password("")
                .role(Role.ROLE_USER)
                .authorities(getAuthorities(claims))
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Collection<? extends SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String authorities = (String) claims.get("authorities");
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
