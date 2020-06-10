package com.mycompany.rottenpotatoes.dao;

import java.net.ConnectException;
import java.util.*;

import com.mycompany.rottenpotatoes.data.Connection;
import com.mycompany.rottenpotatoes.model.Movie;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieDao {

	private static MovieDao instance = new MovieDao();

	public static MovieDao getInstance() {
	    return instance;
	}

    private MovieDao(){}

	public ArrayList<Movie> getAllMovies() throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        Query<Movie> query = session.createQuery("FROM Movie", Movie.class);
        ArrayList<Movie> movies = new ArrayList<Movie>(query.list());

        tx.commit();
        session.close();
        return movies;
	}
}