package com.example.tdd.board.web.dto.jwt;

import com.example.tdd.board.web.dto.users.Role;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@RequiredArgsConstructor
public class JwtUserDetails implements UserDetails {
    private String username;
    private String password;

    private Role role;
    private final Collection<? extends GrantedAuthority> authorities = Collections
            .unmodifiableCollection(Arrays.asList(new SimpleGrantedAuthority(Role.ROLE_USER.getValue())));
    @Builder
    public JwtUserDetails(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
