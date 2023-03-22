package com.search.blog.dto;

import com.search.blog.domain.Blog;
import com.search.blog.infra.dto.KakaoSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

public class KakaoResponse implements SearchResult {
    private final KakaoSearchResponse response;

    public KakaoResponse(SearchResponse<?> response) {
        Object responseData = response.getData();
        if (!(responseData instanceof KakaoSearchResponse)) {
            throw new IllegalArgumentException();
        }
        this.response = (KakaoSearchResponse) responseData;
    }

    @Override
    public int getTotalCount() {
        return response.getMeta().getPageableCount();
    }

    @Override
    public List<Blog> getContent() {
        return response.getDocuments().stream()
                .map(Blog::new)
                .collect(Collectors.toList());
    }
}
