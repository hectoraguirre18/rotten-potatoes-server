package com.mycompany.rottenpotatoes.service;

import java.net.ConnectException;
import java.util.ArrayList;

import com.mycompany.rottenpotatoes.dao.MovieReviewDao;
import com.mycompany.rottenpotatoes.model.MovieReview;

public class MovieReviewService {
    private static MovieReviewService instance;
    public static MovieReviewService getInstance() {
        if(instance == null)
            instance = new MovieReviewService();
        return instance;
    }

    private MovieReviewService(){}

    public ArrayList<MovieReview> getReviewsForMovie(int movieId) throws ConnectException {
        MovieReviewDao dao = MovieReviewDao.getInstance();
        return dao.getReviewsForMovie(movieId);
    }

    public MovieReview saveReview(MovieReview review) throws ConnectException {
        MovieReviewDao dao = MovieReviewDao.getInstance();
        dao.insertReview(review);
        return review;
    }
}