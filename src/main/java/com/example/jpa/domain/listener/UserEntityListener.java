package com.example.jpa.domain.listener;

import com.example.jpa.domain.User;
import com.example.jpa.domain.UserHistory;
import com.example.jpa.repository.UserHistoryRepository;
import com.example.jpa.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

@Configurable
public class UserEntityListener {

    @PreUpdate
    public void preUpdate(Object o) {

       UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User)  o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
