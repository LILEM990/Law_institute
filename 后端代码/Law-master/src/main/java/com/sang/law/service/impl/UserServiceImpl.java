package com.sang.law.service.impl;

import com.sang.law.dao.UserDao;
import com.sang.law.pojo.User;
import com.sang.law.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUser(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public boolean deleteUserById(String id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public User login(String username, String pasaword) {
        return userDao.login(username, pasaword);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
}
