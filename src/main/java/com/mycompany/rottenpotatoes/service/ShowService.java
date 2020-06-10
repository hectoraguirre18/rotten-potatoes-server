package com.mycompany.rottenpotatoes.service;

import java.net.ConnectException;
import java.util.ArrayList;

import com.mycompany.rottenpotatoes.dao.ShowDao;
import com.mycompany.rottenpotatoes.model.Show;

public class ShowService {
    private static ShowService instance;
    public static ShowService getInstance() {
        if(instance == null)
            instance = new ShowService();
        return instance;
    }

    private ShowService(){}

    public ArrayList<Show> getAllShows() throws ConnectException {
        ShowDao dao = ShowDao.getInstance();
        return dao.getAllShows();
    }

    public Show saveShow(Show show) throws ConnectException {
        ShowDao dao = ShowDao.getInstance();
        dao.insertShow(show);
        return show;
    }
}