package com.example.jpa.service;

import com.example.jpa.domain.User;
import com.example.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class EntityMangerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Test
    void entityMangerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    void cacheFindTest() {
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findById(1L).get());

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2() {
        User user = userRepository.findById(1L).get();
        user.setName(("adasd"));

        userRepository.save(user);
        user.setEmail(("asd@.com"));

        userRepository.save(user);

        System.out.println(userRepository.findById(1L).get());
    }
}
