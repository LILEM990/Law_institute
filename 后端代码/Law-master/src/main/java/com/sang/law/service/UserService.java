package com.sang.law.service;

import com.sang.law.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    boolean addUser(User user);

    User getUser(String id);

    boolean deleteUserById(String id);

    User login(String username, String pasaword);

    boolean updateUser(User user);
}
