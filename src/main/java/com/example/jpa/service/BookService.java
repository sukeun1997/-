package com.example.jpa.service;

import com.example.jpa.domain.Author;
import com.example.jpa.domain.Book;
import com.example.jpa.repository.AuthorRepository;
import com.example.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");
        authorRepository.save(author);

        throw new RuntimeException("중간에 오류나서 commit x");

    }
}
