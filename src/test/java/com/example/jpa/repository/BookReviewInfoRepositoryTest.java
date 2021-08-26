package com.example.jpa.repository;

import com.example.jpa.domain.*;
import com.example.jpa.repository.dto.BookStatus;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.support.PropertyProvider;

import javax.transaction.Transactional;

import java.awt.geom.QuadCurve2D;
import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    @Transactional
    void bookCscadeTest() {
        Book book = new Book();
        book.setName("Jpa ");

        Publisher publisher = new Publisher();
        publisher.setName("FasCam");


        publisher.addBook(book);
        publisherRepository.saveAndFlush(publisher);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publisher : " + publisherRepository.findAll());

        Publisher publisher1 = publisherRepository.findById(1L).get();
        publisher1.getBookList().get(0).setName("변경");

        publisherRepository.saveAndFlush(publisher1);

        publisher.getBookList().clear();

        System.out.println("Books :" + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

    }
    @Test
    @Transactional
    void bookRemoveCascadeTest() {

        System.out.println("books : " + bookRepository.findAll());

        System.out.println("publisher : " + publisherRepository.findAll());

        publisherRepository.findAll().forEach(publisher -> publisher.getBookList());

        publisherRepository.findById(1L).get().setBookList(null);
        publisherRepository.deleteById(1L);

        System.out.println("books : " + bookRepository.findAll());
    }

    @Test
    void queryTest() {
        System.out.println("findByNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual" + bookRepository.findByNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "jpa초격차패키지",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)));

        System.out.println("findByNameRecently" + bookRepository.findByNameRecently("jpa초격차패키지",LocalDateTime.now().minusDays(1L),LocalDateTime.now().minusDays(1L)));
    }

    @Test
    void natvieQueryTest() {

//        List<Book> books = bookRepository.findAll();
//
//        for (Book book : books) {
//           book.setCategory("jpa 전문서");
//        }
//
//        bookRepository.saveAll(books);
//        System.out.println(bookRepository.findAll());

        System.out.println("affected rows : " + bookRepository.updateCategories());
        System.out.println(bookRepository.findAllCustom());

    }

    @Test
    void converterTest() {
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("전문서적");
        book.setStatus(new BookStatus(200));
        bookRepository.save(book);

        System.out.println(bookRepository.findRowRecord().values());
    }
}