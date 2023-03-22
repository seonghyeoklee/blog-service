package com.search.blog.ui;

import com.search.blog.application.KeywordService;
import com.search.blog.application.SearchService;
import com.search.blog.domain.Blog;
import com.search.blog.dto.RankResponse;
import com.search.blog.dto.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final KeywordService keywordService;

    @GetMapping("/blog")
    public ResponseEntity<?> searchBlog(
            @RequestParam(value = "blogUrl", required = false) String blogUrl,
            @RequestParam("search") String search,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        Page<Blog> response = searchService.search(new SearchRequest(blogUrl, search, sort, page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/keyword")
    public ResponseEntity<?> keyword() {
        List<RankResponse> rankResponse = keywordService.getRank();
        return ResponseEntity.ok(rankResponse);
    }
}
