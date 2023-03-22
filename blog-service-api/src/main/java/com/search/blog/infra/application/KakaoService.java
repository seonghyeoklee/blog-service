package com.search.blog.infra.application;

import com.search.blog.dto.SearchRequest;
import com.search.blog.dto.SearchResponse;
import com.search.blog.exception.KakaoSearchException;
import com.search.blog.infra.dto.KakaoSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

@Service
public class KakaoService implements SearchPolicy {
    private final WebClient kakaoWebClient;

    public KakaoService(WebClient kakaoWebClient) {
        this.kakaoWebClient = kakaoWebClient;
    }

    @Override
    public Function<UriBuilder, URI> getUriFunction(SearchRequest request) {
        return uriBuilder -> uriBuilder.path("/v2/search/blog")
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort())
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .build();
    }

    @Override
    public SearchResponse<KakaoSearchResponse> search(SearchRequest request) {
        try {
            KakaoSearchResponse kakaoSearchResponse = kakaoWebClient
                    .get()
                    .uri(this.getUriFunction(request))
                    .header("Authorization", "KakaoAK 761f6463533165a956d5fed2717ae722")
                    .retrieve()
                    .bodyToMono(KakaoSearchResponse.class)
                    .block();

            return new SearchResponse<>(kakaoSearchResponse);
        } catch (Exception e) {
            throw new KakaoSearchException(e);
        }
    }
}
