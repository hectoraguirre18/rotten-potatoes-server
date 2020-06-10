package com.mycompany.rottenpotatoes.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.ConnectException;

public class Connection 
{
	private static Connection instance; 
    private SessionFactory sessionFactory;
    
    public static Connection getInstance() {
    	if(null == instance) {
    		instance = new Connection();
    	}
        return instance;
    }
    
    private Connection() {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");    
            sessionFactory = configuration.buildSessionFactory();  
    }
    
    public static Session getSession() throws ConnectException { 
        try {
            Session session = getInstance().sessionFactory.openSession();
            return session;
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        } 
    }
}