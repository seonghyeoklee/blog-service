package com.search.blog.infra;

import com.search.event.Event;

public class KeywordCountingEvent extends Event {
    private final String keyword;

    public KeywordCountingEvent(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
