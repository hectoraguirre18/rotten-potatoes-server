package com.mycompany.rottenpotatoes.dao;

import java.net.ConnectException;
import java.util.*;

import com.mycompany.rottenpotatoes.data.Connection;
import com.mycompany.rottenpotatoes.model.Show;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShowDao {

	private static ShowDao instance = new ShowDao();

	public static ShowDao getInstance() {
	    return instance;
	}

    private ShowDao(){}

	public ArrayList<Show> getAllShows() throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        Query<Show> query = session.createQuery("FROM Show", Show.class);
        ArrayList<Show> shows = new ArrayList<Show>(query.list());

        tx.commit();
        session.close();
        return shows;
	}

    public void insertShow(Show show) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        session.save(show);

        tx.commit();
        session.close();
    }
}