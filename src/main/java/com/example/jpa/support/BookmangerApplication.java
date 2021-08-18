package com.example.jpa.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmangerApplication.class, args);
    }
}
