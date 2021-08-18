package com.example.jpa.domain;

import com.example.jpa.domain.listener.Auditable;
import com.example.jpa.domain.listener.MyEntityListener;
import com.example.jpa.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = {MyEntityListener.class , UserEntityListener.class})
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

}
