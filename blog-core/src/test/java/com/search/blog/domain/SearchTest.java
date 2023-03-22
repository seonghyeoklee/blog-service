package com.search.blog.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("검색 단위 테스트")
class SearchTest {

    @DisplayName("검색 카운트는 1씩 증가한다.")
    @Test
    void increase() {
        Search search = new Search("검색어");
        search.increase();
        assertThat(search.getCount()).isEqualTo(2);
    }
}
