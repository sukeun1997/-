package com.example.jpa.service;

import com.example.jpa.domain.Author;
import com.example.jpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.NESTED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("오류 임의 발생");
    }
}
