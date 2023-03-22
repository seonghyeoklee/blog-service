package com.search.blog.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum KakaoSort {
    ACCURACY("accuracy", "정확도순"),
    RECENCY("recency", "최신순"),
    ;

    private final String code;
    private final String description;

    KakaoSort(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static KakaoSort findByCode(String target) {
        return Arrays.stream(values())
                .filter(kakaoSort -> kakaoSort.getCode().equals(target))
                .findFirst()
                .orElse(ACCURACY);
    }

    public static NaverSort findBySort(String target) {
        KakaoSort kakaoSort = findByCode(target);
        if (KakaoSort.ACCURACY.equals(kakaoSort)) {
            return NaverSort.SIM;
        }
        return NaverSort.DATE;
    }
}
