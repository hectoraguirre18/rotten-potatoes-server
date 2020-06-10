package com.mycompany.rottenpotatoes.dao;

import java.net.ConnectException;
import java.util.*;

import com.mycompany.rottenpotatoes.data.Connection;
import com.mycompany.rottenpotatoes.model.User;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
	    return instance;
	}

    private UserDao(){}

    public void saveUser(User user) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;

        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        session.save(user);

        tx.commit();
        session.close();
    }

    public Integer getUserToken(User user) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        String queryString = "FROM User WHERE name = :name AND password = :password";

        Query<User> query = session.createQuery(queryString, User.class);
        query.setParameter("name", user.getName());
        query.setParameter("password", user.getPassword());
        
        ArrayList<User> users = new ArrayList<User>(query.list());

        tx.commit();
        session.close();

        if(users != null && users.size() > 0)
            return users.get(0).getId();
        else
            return null;
    }
}