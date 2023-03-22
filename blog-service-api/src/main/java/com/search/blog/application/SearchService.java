package com.search.blog.application;

import com.search.blog.domain.Blog;
import com.search.blog.dto.KakaoResponse;
import com.search.blog.dto.NaverResponse;
import com.search.blog.dto.SearchRequest;
import com.search.blog.dto.SearchResponse;
import com.search.blog.exception.CannotSearchException;
import com.search.blog.exception.KakaoSearchException;
import com.search.blog.infra.KeywordCountingEvent;
import com.search.blog.infra.application.SearchPolicy;
import com.search.event.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SearchService {
    private final SearchPolicy kakaoService;
    private final SearchPolicy naverService;

    public SearchService(SearchPolicy kakaoService, SearchPolicy naverService) {
        this.kakaoService = kakaoService;
        this.naverService = naverService;
    }

    @Transactional
    public Page<Blog> search(SearchRequest request) {
        Page<Blog> blogPage;
        try {
            blogPage = kakaoSearchResult(request);
        } catch (KakaoSearchException e) {
            log.warn("카카오 블로그 검색 서비스를 이용할 수 없습니다.");
            blogPage = naverSearchResult(request);
        }

        Events.publish(new KeywordCountingEvent(request.getSearch()));
        return blogPage;
    }

    private Page<Blog> kakaoSearchResult(SearchRequest request) {
        SearchResponse<?> search = kakaoService.search(request);
        KakaoResponse response = new KakaoResponse(search);

        return new PageImpl<>(response.getContent(), PageRequest.of(request.getPage(), request.getSize()), response.getTotalCount());
    }

    private Page<Blog> naverSearchResult(SearchRequest request) {
        try {
            SearchResponse<?> search = naverService.search(request);
            NaverResponse response = new NaverResponse(search);

            return new PageImpl<>(response.getContent(), PageRequest.of(request.getPage(), request.getSize()), response.getTotalCount());
        } catch (Exception e) {
            log.error("네이버 블로그 검색 서비스를 이용할 수 없습니다.");
            throw new CannotSearchException("검색 서비스가 원할하지 않습니다. 잠시 후에 다시 시도해주세요.", e);
        }
    }
}
