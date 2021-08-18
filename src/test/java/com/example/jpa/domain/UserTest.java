package com.example.jpa.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("asd@af.com");
        user.setName("morit");
    }
}
