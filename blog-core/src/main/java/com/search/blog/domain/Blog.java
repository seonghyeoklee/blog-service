package com.search.blog.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Blog {
    private String title;
    private String content;
    private String blogname;
    private String url;
    private String datetime;
    private String thumbnail;

    public Blog(Document document) {
        this.title = document.getTitle();
        this.content = document.getContents();
        this.blogname = document.getBlogname();
        this.url = document.getUrl();
        this.datetime = document.getDatetime();
        this.thumbnail = document.getThumbnail();
    }

    public Blog(Item item) {
        this.title = item.getTitle();
        this.content = item.getDescription();
        this.blogname = item.getBloggername();
        this.url = item.getBloggerlink();
        this.datetime = item.getPostdate();
        this.thumbnail = null;
    }
}
