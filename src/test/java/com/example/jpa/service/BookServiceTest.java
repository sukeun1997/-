package com.example.jpa.service;

import com.example.jpa.domain.Book;
import com.example.jpa.repository.AuthorRepository;
import com.example.jpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try {
//            bookService.put();
            bookService.putBookAndAuthor();
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("jpa 강으");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>>" + bookRepository.findAll());
    }
}