package com.mycompany.rottenpotatoes.service;

import java.net.ConnectException;
import java.util.ArrayList;

import com.mycompany.rottenpotatoes.dao.ShowReviewDao;
import com.mycompany.rottenpotatoes.model.ShowReview;

public class ShowReviewService {
    private static ShowReviewService instance;
    public static ShowReviewService getInstance() {
        if(instance == null)
            instance = new ShowReviewService();
        return instance;
    }

    private ShowReviewService(){}

    public ArrayList<ShowReview> getReviewsForShow(int showId) throws ConnectException {
        ShowReviewDao dao = ShowReviewDao.getInstance();
        return dao.getReviewsForShow(showId);
    }

    public ShowReview saveReview(ShowReview review) throws ConnectException {
        ShowReviewDao dao = ShowReviewDao.getInstance();
        System.out.println("[ShowReviewService][saveReview] 1");
        dao.insertReview(review);
        System.out.println("[ShowReviewService][saveReview] 2");
        return review;
    }
}