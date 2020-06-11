package com.mycompany.rottenpotatoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "showreviews")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowReview {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private int showId;
    
    @Column
    private int userId;
    
    @Column
    private String userName;
    
    @Column
    private String content;
    
    @Column
    private Integer parentShowReview;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowId() {
        return this.showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
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

    public Integer getParentShowReview() {
        return this.parentShowReview;
    }

    public void setParentShowReview(Integer parentShowReview) {
        this.parentShowReview = parentShowReview;
    }

}