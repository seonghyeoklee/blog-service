package com.search.blog.dto;

public class SearchResponse<T> {
    private final T data;

    public SearchResponse(T data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
