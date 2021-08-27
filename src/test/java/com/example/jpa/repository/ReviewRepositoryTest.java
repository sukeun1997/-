package com.example.jpa.repository;

import com.example.jpa.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional(readOnly = true)
    void reviewTest() {
        List<Review> reviews = reviewRepository.findAll();

        System.out.println(reviewRepository.findAllByEntityGraph());
    }
}
