package com.example.jpa.repository;

import com.example.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameAndEmail(String name, String email);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByIdAfter(Long id);

    List<User> findByIdBetween(Long idb, Long ida);

    List<User> findByNameIn(List name);

    List<User> findByNameContaining(String name);

    List<User> findByNameOrderByIdDesc(String name);

    List<User> findByName(String name, Sort sort);

    User findByEmail(String email);

    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String,Object> findRowRecord();
}
