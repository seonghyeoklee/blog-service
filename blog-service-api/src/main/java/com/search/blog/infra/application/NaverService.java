package com.search.blog.infra.application;

import com.search.blog.domain.KakaoSort;
import com.search.blog.dto.SearchRequest;
import com.search.blog.dto.SearchResponse;
import com.search.blog.exception.NaverSearchException;
import com.search.blog.infra.dto.NaverSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

@Service
public class NaverService implements SearchPolicy {
    private final WebClient naverWebClient;

    public NaverService(WebClient naverWebClient) {
        this.naverWebClient = naverWebClient;
    }

    @Override
    public Function<UriBuilder, URI> getUriFunction(SearchRequest request) {
        return builder -> builder.path("/v1/search/blog")
                .queryParam("query", request.getQuery())
                .queryParam("display", request.getSize())
                .queryParam("start", request.getPage())
                .queryParam("sort", KakaoSort.findBySort(request.getSort()))
                .build();
    }

    @Override
    public SearchResponse<NaverSearchResponse> search(SearchRequest request) {
        try {
            NaverSearchResponse naverSearchResponse = naverWebClient
                    .get()
                    .uri(this.getUriFunction(request))
                    .header("X-Naver-Client-Id", "ZYlMICUeCSLGNYBC8n_f")
                    .header("X-Naver-Client-Secret", "s4wOyHHcGa")
                    .retrieve()
                    .bodyToMono(NaverSearchResponse.class)
                    .block();

            return new SearchResponse<>(naverSearchResponse);
        } catch (Exception e) {
            throw new NaverSearchException(e);
        }
    }
}
