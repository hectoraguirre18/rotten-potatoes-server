package com.mycompany.rottenpotatoes.service;

import java.net.ConnectException;

import com.mycompany.rottenpotatoes.dao.UserDao;
import com.mycompany.rottenpotatoes.model.User;

public class UserService {
    private static UserService instance;
    public static UserService getInstance() {
        if(instance == null)
            instance = new UserService();
        return instance;
    }

    private UserService(){}

    public Integer getUserToken(User user) throws ConnectException {
        UserDao dao = UserDao.getInstance();
        return dao.getUserToken(user);
    }

    public User registerUser(User user) throws ConnectException {
        UserDao dao = UserDao.getInstance();
        dao.saveUser(user);
        return user;
    }
}