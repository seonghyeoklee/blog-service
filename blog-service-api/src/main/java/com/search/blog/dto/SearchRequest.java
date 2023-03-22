package com.search.blog.dto;

import com.search.blog.domain.KakaoSort;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Getter
@ToString
public class SearchRequest {
    private String blogUrl;
    private String search;
    private String sort;
    private Integer page;
    private Integer size;

    public SearchRequest(String blogUrl, String search, String sort, Integer page, Integer size) {
        this.blogUrl = blogUrl.trim();
        this.search = Objects.requireNonNull(search.trim());
        this.sort = KakaoSort.findByCode(sort.trim()).getCode();
        this.page = Objects.isNull(page) ? 1 : page;
        this.size = Objects.isNull(size) ? 10 : size;
    }

    public String getQuery() {
        if (StringUtils.hasText(this.blogUrl)) {
            return this.blogUrl + " " + this.search;
        }
        return this.search;
    }
}
