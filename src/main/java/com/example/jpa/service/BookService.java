package com.example.jpa.service;

import com.example.jpa.domain.Author;
import com.example.jpa.domain.Book;
import com.example.jpa.repository.AuthorRepository;
import com.example.jpa.repository.BookRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {

        }
//        throw new RuntimeException("오류 임의 발생");

    }

   @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void get(Long id) {
        System.out.println(">>>>" + bookRepository.findById(id));
        System.out.println(">>>>" + bookRepository.findAll());

        System.out.println(">>>>" + bookRepository.findById(id));
        System.out.println(">>>>" + bookRepository.findAll());

       Book book = bookRepository.findById(id).get();
       book.setName("asds");
       bookRepository.save(book);

       System.out.println(bookRepository.findRowRecord());
    }
}
