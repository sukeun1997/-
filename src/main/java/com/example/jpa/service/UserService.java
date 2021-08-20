package com.example.jpa.service;

import com.example.jpa.domain.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() { // 비영속
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");
        entityManager.persist(user);
        entityManager.detach(user);

        user.setName("newUserAfterPersist");
        entityManager.merge(user);
        user.setName("asd");
        entityManager.flush();
    }


}
