package com.search.blog.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Document {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;
}
