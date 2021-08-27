package com.example.jpa.domain;

import com.example.jpa.domain.converter.BookSatusConverter;
import com.example.jpa.repository.dto.BookStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javassist.expr.NewArray;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    @OneToOne(mappedBy = "book")
    private BookReviewInfo bookReviewInfo;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthor = new ArrayList<>();

//    @Convert(converter = BookSatusConverter.class)
    private BookStatus status;

    public void addBookAndAuthor(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthor, bookAndAuthors);
    }
}
