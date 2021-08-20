package com.example.jpa.repository;

import com.example.jpa.domain.*;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.support.PropertyProvider;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook(givenPublisher()));
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);


        bookReviewInfoRepository.save(bookReviewInfo);
        System.out.println(bookReviewInfoRepository.findAll());
    }

    @Test
    @Transactional
    void crudTest2() {

        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook(givenPublisher()));
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
        bookReviewInfo = bookReviewInfoRepository.findById(1L).get();
        System.out.println(">>> " + bookReviewInfoRepository.findById(1L).get());
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();

        book.setName("jpa 초격차 패키지");
        book.setAuthorId(1L);
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook(givenPublisher()));
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findById(1L));
    }


    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        return userRepository.findById(1L).orElseThrow(RuntimeException::new);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("jap");
        review.setContent("good");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스틐ㅁ퍼슽");

        return publisherRepository.save(publisher);
    }
}