package com.search.blog.application;

import com.search.blog.domain.Search;
import com.search.blog.domain.SearchRepository;
import com.search.blog.dto.RankResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {
    private final SearchRepository searchRepository;

    @Transactional
    public synchronized void increase(String target) {
        Optional<Search> findKeyword = searchRepository.findByKeyword(target);
        if (findKeyword.isEmpty()) {
            searchRepository.saveAndFlush(new Search(target));
            return;
        }

        findKeyword.ifPresent(search -> {
            search.increase();
            log.info(" >>> increase keyword count={}", search.getCount());
        });
    }

    public List<RankResponse> getRank() {
        List<Search> countDesc = searchRepository.findTop10ByOrderByCountDesc();
        return getRankResponses(countDesc);
    }

    private static List<RankResponse> getRankResponses(List<Search> countDesc) {
        List<RankResponse> responses = new ArrayList<>();
        for (int i = 0; i < countDesc.size(); i++) {
            responses.add(new RankResponse(i + 1, countDesc.get(i)));
        }
        return responses;
    }
}
