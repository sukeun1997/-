package com.example.jpa.repository;

import com.example.jpa.domain.Book;
import com.example.jpa.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
