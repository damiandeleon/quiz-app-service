package com.company.quizappservice.dao;

import com.company.quizappservice.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);
}