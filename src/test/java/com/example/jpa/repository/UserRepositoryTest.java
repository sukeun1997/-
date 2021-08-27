package com.example.jpa.repository;


import com.example.jpa.domain.Gender;
import com.example.jpa.domain.User;
import com.example.jpa.domain.UserHistory;
import com.example.jpa.domain.listener.Address;
import org.assertj.core.util.Lists;
import org.hibernate.criterion.Order;
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

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("david");
        user.setEmail("davis@campus.com");
        user.setGender(Gender.Male);

        userRepository.save(user);

        user.setName("dinai");
        userRepository.save(user);

        user.setEmail("dinai@fastcaom.com");
        userRepository.save(user);

        System.out.println(userHistoryRepository.findAll());
        List<UserHistory> result = userRepository.findByEmail("dinai@fastcaom.com").getUserHistories();

        result.forEach(System.out::println);
    }

    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steave");
        user.setAddress(new Address("서울시", "강남구", "강남대로 364 미암빌딩","06241"));
        user.setHomeAddress(new Address("서울시", "성동구",  "성수이로", "02134"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jardon");
        user2.setHomeAddress(new Address());
        user2.setAddress(new Address());
        userRepository.save(user2);

        userRepository.findAll().forEach(System.out::println);
    }
}