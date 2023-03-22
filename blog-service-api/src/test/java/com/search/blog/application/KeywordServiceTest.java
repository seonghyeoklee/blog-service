package com.search.blog.application;

import com.search.blog.domain.Search;
import com.search.blog.domain.SearchRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KeywordServiceTest {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private SearchRepository searchRepository;

    @DisplayName("100건이 동시에 요청되는 경우")
    @Test
    void synchronizedTest() throws Exception {
        // given
        int threadCount = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    keywordService.increase("검색어");
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Search search = searchRepository.findById(1L).orElseThrow();

        // then
        assertEquals(100, search.getCount());
    }
}
