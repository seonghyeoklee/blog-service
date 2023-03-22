package com.search.blog.infra.application;

import com.search.blog.dto.SearchRequest;
import com.search.blog.dto.SearchResponse;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public interface SearchPolicy {
    Function<UriBuilder, URI> getUriFunction(SearchRequest request);

    SearchResponse<?> search(SearchRequest request);
}
