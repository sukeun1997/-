package com.example.jpa.repository;

import com.example.jpa.domain.Book;
import com.example.jpa.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.support.PropertyProvider;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;


    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();

        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());
    }

    @Test
    void crudTest2() {
        Book book = new Book();

        book.setName("jpa 초격차 패키지");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> "  + bookReviewInfoRepository.findAll());

        Book result = bookRepository.findById(
                bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBookId()
        ).orElseThrow(RuntimeException::new);

        System.out.println(">>>>" + result);



    }
}