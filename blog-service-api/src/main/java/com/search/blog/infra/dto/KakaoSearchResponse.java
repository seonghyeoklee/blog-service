package com.search.blog.infra.dto;

import com.search.blog.domain.Document;
import com.search.blog.domain.Meta;

import java.util.List;

public class KakaoSearchResponse {
    private Meta meta;
    private List<Document> documents;

    public KakaoSearchResponse() {}

    public KakaoSearchResponse(Meta meta, List<Document> documents) {
        this.meta = meta;
        this.documents = documents;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "meta=" + meta +
                ", documents=" + documents +
                '}';
    }
}
