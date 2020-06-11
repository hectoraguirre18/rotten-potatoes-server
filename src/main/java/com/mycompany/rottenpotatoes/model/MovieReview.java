package com.mycompany.rottenpotatoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "moviereviews")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieReview {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private int movieId;
    
    @Column
    private int userId;
    
    @Column
    private String userName;
    
    @Column
    private String content;
    
    @Column
    private Integer parentMovieReview;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentMovieReview() {
        return this.parentMovieReview;
    }

    public void setParentMovieReview(Integer parentMovieReview) {
        this.parentMovieReview = parentMovieReview;
    }

}