package com.company.quizappservice.dao;

import com.company.quizappservice.dto.User;

import java.util.List;

public interface UserDao {

    public User addUser(User user);

    public User getUser(int id);

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(int id);
}
