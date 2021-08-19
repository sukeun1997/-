package com.example.jpa.repository;

import com.example.jpa.domain.User;
import com.example.jpa.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {

    List<UserHistory> findByUserId(Long userid);
}
