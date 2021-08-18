package com.example.jpa.repository;

import com.example.jpa.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {
}
