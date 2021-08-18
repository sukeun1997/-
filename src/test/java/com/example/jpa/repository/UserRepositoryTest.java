package com.example.jpa.repository;


import com.example.jpa.domain.Gender;
import com.example.jpa.domain.User;
import com.example.jpa.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository   userHistoryRepository;

    @Test
    void crud() { // create ,read , update , delete
        userRepository.save(new User("david", "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-update@fastcampus.com");

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void select() {
        System.out.println("findBynameandemail :" + userRepository.findByNameAndEmail("martin", "martin@fastcampus.com"));
        System.out.println("findByNameOrEmail :" + userRepository.findByNameOrEmail("james", "martin@fastcampus.com"));
        System.out.println("findByidAfter : " + userRepository.findByIdAfter(1L));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("james","martin")));
        System.out.println("findByNameContaining : " + userRepository.findByNameContaining("ar"));
    }

    @Test
    void pagingAndSortingTest() {
//        System.out.println("findByNameOrderByIdDesc : " + userRepository.findByNameOrderByIdDesc("martin"));
//        System.out.println("findByNameSort : " + userRepository.findByName("martin",Sort.by(Sort.Order.desc("id"))));
        System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(1,1, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void enumTest() {
        User user  = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.Male);
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));
    }


    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("asd");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("martin2");
        user.setName("martin");

        userRepository.save(user);

        System.out.println(userRepository.findByNameOrEmail("","martin2"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is :"  + user);

        user.setName("Maritn22");
        userRepository.save(user);
        System.out.println("to-be" + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin-new@fstcampus.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("martin-new-new");

        userRepository.save(user);
        userHistoryRepository.findAll().forEach(System.out::println);
    }

}