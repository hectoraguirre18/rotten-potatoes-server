package com.mycompany.rottenpotatoes.dao;

import java.net.ConnectException;
import java.util.*;

import com.mycompany.rottenpotatoes.data.Connection;
import com.mycompany.rottenpotatoes.model.ShowReview;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShowReviewDao {

	private static ShowReviewDao instance = new ShowReviewDao();

	public static ShowReviewDao getInstance() {
	    return instance;
	}

    private ShowReviewDao(){}

	public ArrayList<ShowReview> getReviewsForShow(int showId) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        String queryString = "FROM ShowReview WHERE showId = :showId";
        Query<ShowReview> query = session.createQuery(queryString, ShowReview.class);
        System.out.println("showId: " + showId);
        query.setParameter("showId", showId);

        ArrayList<ShowReview> reviews = new ArrayList<ShowReview>(query.list());

        tx.commit();
        session.close();
        return reviews;
	}

    public void insertReview(ShowReview review) throws ConnectException {
        Session session = Connection.getSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            System.err.println("An error ocurred while connecting to the database servers.");
            throw new ConnectException();
        }

        System.out.println("[ShowReviewDao][insertReview] 1");
        session.save(review);
        System.out.println("[ShowReviewDao][insertReview] 2");

        tx.commit();
        session.close();
    }
}