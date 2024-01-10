package com.example.tdd.board.web.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Image {
    private String originName;
    private String changedName;
    private String storedImageUrlPath;

    @Builder
    public Image(String originName, String changedName, String storedImageUrlPath) {
        this.originName = originName;
        this.changedName = changedName;
        this.storedImageUrlPath = storedImageUrlPath;
    }
}
