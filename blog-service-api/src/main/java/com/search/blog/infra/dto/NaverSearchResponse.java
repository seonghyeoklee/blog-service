package com.search.blog.infra.dto;

import com.search.blog.domain.Item;

import java.util.List;

public class NaverSearchResponse {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<Item> items;

    public NaverSearchResponse() {}

    public NaverSearchResponse(String lastBuildDate, Integer total, Integer start, Integer display, List<Item> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getDisplay() {
        return display;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "NaverSearchResponse{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                ", items=" + items +
                '}';
    }
}
