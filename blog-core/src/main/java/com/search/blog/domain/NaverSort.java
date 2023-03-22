package com.search.blog.domain;

import lombok.Getter;

@Getter
public enum NaverSort {
    SIM("sim", "정확도순"),
    DATE("date", "최신순"),
    ;

    private final String code;
    private final String description;

    NaverSort(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
