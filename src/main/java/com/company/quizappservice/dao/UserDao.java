package com.company.quizappservice.dao;

import com.company.quizappservice.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    public User addUser(User user);

    public User getUser(int id);

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(int id);
}