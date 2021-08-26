package com.example.jpa.repository;

import com.example.jpa.domain.Book;
import com.example.jpa.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    @Modifying
    @Transactional
    @Query(value = "update book set category = 'jpa전문서'",nativeQuery = true)
    int updateCategories();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRowRecord();
}
