package com.search.blog.dto;

import com.search.blog.domain.Search;
import lombok.Getter;

@Getter
public class RankResponse {
    private int rank;
    private String keyword;
    private Long count;

    public RankResponse(int rank, Search search) {
        this.rank = rank;
        this.keyword = search.getKeyword();
        this.count = search.getCount();
    }
}
