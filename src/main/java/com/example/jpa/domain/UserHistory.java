package com.example.jpa.domain;

import com.example.jpa.domain.listener.Auditable;
import com.example.jpa.domain.listener.MyEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = MyEntityListener.class)
public class UserHistory implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
