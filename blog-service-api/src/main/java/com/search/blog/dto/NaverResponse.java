package com.search.blog.dto;

import com.search.blog.domain.Blog;
import com.search.blog.infra.dto.NaverSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

public class NaverResponse implements SearchResult {
    private final NaverSearchResponse response;

    public NaverResponse(SearchResponse<?> response) {
        Object responseData = response.getData();
        if (!(responseData instanceof NaverSearchResponse)) {
            throw new IllegalArgumentException();
        }
        this.response = (NaverSearchResponse) responseData;
    }

    @Override
    public int getTotalCount() {
        return response.getTotal();
    }

    @Override
    public List<Blog> getContent() {
        return response.getItems().stream()
                .map(Blog::new)
                .collect(Collectors.toList());
    }
}
