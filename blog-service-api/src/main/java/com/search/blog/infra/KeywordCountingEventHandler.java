package com.search.blog.infra;

import com.search.blog.application.KeywordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class KeywordCountingEventHandler {
    private final KeywordService keywordService;

    public KeywordCountingEventHandler(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @Async
    @TransactionalEventListener(classes = KeywordCountingEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(KeywordCountingEvent event) {
        keywordService.increase(event.getKeyword());
    }
}
