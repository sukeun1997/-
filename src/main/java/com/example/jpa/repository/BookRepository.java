package com.example.jpa.repository;

import com.example.jpa.domain.Book;
import com.example.jpa.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
             String name,
             LocalDateTime createdAt,
             LocalDateTime updatedAt);

    @Query(value = "select b from Book b where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt")
    List<Book> findByNameRecently(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);


}
