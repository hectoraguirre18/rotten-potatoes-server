package com.mycompany.rottenpotatoes.dao;

import java.net.ConnectException;
import java.util.*;

import com.mycompany.rottenpotatoes.data.Connection;
import com.mycompany.rottenpotatoes.model.MovieReview;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieReviewDao {

	private static MovieReviewDao instance = new MovieReviewDao();

	public static MovieReviewDao getInstance() {
	    return instance;
	}

    private MovieReviewDao(){}

	public ArrayList<MovieReview> getReviewsForMovie(int movieId) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        String queryString = "FROM MovieReview WHERE movieId = :movieId";
        Query<MovieReview> query = session.createQuery(queryString, MovieReview.class);
        System.out.println("movieId: " + movieId);
        query.setParameter("movieId", movieId);

        ArrayList<MovieReview> reviews = new ArrayList<MovieReview>(query.list());

        tx.commit();
        session.close();
        return reviews;
	}

    public void insertReview(MovieReview review) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        session.save(review);

        tx.commit();
        session.close();
    }
}