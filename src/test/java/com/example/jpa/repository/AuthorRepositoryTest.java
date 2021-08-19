package com.example.jpa.repository;

import com.example.jpa.domain.Author;
import com.example.jpa.domain.Book;
import com.example.jpa.domain.BookAndAuthor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;
    @Test
    @Transactional
    void manytomanytest() {

        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("책3");
        Book book4 = givenBook("책4");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steave");

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);

        book1.addBookAndAuthor(bookAndAuthor1);
        book2.addBookAndAuthor(bookAndAuthor2);
        book3.addBookAndAuthor(bookAndAuthor3,bookAndAuthor4);
        book4.addBookAndAuthor(bookAndAuthor5,bookAndAuthor6);

        author1.addBookAndAuthor(bookAndAuthor1,bookAndAuthor3,bookAndAuthor5);
        author2.addBookAndAuthor(bookAndAuthor2,bookAndAuthor4,bookAndAuthor6);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));
        bookAndAuthorRepository.saveAll(Lists.newArrayList(bookAndAuthor1, bookAndAuthor2, bookAndAuthor3, bookAndAuthor4, bookAndAuthor5, bookAndAuthor6));

        bookRepository.findAll().get(2).getBookAndAuthor().forEach(o -> System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthor().forEach(o -> System.out.println(o.getBook()));



    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {

        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {
        BookAndAuthor bookAndAuthor = new BookAndAuthor();

        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);
        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}