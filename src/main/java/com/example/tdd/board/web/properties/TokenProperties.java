package com.example.tdd.board.web.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@Setter
@ConstructorBinding
public class TokenProperties {

    private String secretKey;

    private long expireLength;

    public TokenProperties() {
    }
}
