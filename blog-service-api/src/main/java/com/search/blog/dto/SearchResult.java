package com.search.blog.dto;

import com.search.blog.domain.Blog;

import java.util.List;

public interface SearchResult {
    int getTotalCount();

    List<Blog> getContent();
}
