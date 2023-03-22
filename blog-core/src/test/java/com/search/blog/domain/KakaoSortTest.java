package com.search.blog.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("정렬 단위 테스트")
class KakaoSortTest {

    @DisplayName("카카오 정렬 코드 조회 테스트")
    @Test
    void findByCode() {
        KakaoSort 정확도순 = KakaoSort.findByCode("accuracy");
        KakaoSort 최신순 = KakaoSort.findByCode("recency");

        assertThat(정확도순).isEqualTo(KakaoSort.ACCURACY);
        assertThat(최신순).isEqualTo(KakaoSort.RECENCY);
    }

    @DisplayName("네이버 정렬 코드 조회 테스트")
    @Test
    void findBySort() {
        NaverSort 정확도순 = KakaoSort.findBySort("accuracy");
        NaverSort 최신순 = KakaoSort.findBySort("recency");

        assertThat(정확도순).isEqualTo(NaverSort.SIM);
        assertThat(최신순).isEqualTo(NaverSort.DATE);
    }
}
