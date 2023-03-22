package com.search.blog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyword;
    private Long count;

    public Search(String keyword) {
        this.keyword = keyword;
        this.count = 1L;
    }

    public void increase() {
        this.count += 1;
    }

    @Override
    public String toString() {
        return "Search{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                ", count=" + count +
                '}';
    }
}
