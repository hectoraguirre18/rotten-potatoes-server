package com.mycompany.rottenpotatoes.service;

import java.net.ConnectException;
import java.util.ArrayList;

import com.mycompany.rottenpotatoes.dao.MovieDao;
import com.mycompany.rottenpotatoes.model.Movie;

public class MovieService {
    private static MovieService instance;
    public static MovieService getInstance() {
        if(instance == null)
            instance = new MovieService();
        return instance;
    }

    private MovieService(){}

    public ArrayList<Movie> getAllMovies() throws ConnectException {
        MovieDao dao = MovieDao.getInstance();
        return dao.getAllMovies();
    }

    public Movie saveMovie(Movie movie) throws ConnectException {
        MovieDao dao = MovieDao.getInstance();
        dao.insertMovie(movie);
        return movie;
    }
}