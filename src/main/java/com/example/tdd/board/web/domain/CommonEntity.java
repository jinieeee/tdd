package com.example.tdd.board.web.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class CommonEntity {
    protected LocalDateTime sysRegDtime = LocalDateTime.now();
    protected LocalDateTime sysModDtime = LocalDateTime.now();
}
