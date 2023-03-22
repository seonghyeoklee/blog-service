package com.search.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Search s where s.keyword = :keyword")
    Optional<Search> findByKeyword(@Param("keyword") String keyword);

    List<Search> findTop10ByOrderByCountDesc();
}
