package com.example.quickmathsgame.Model;

public class LeaderboardModel {
    //private String score;
    private Integer score;
    private String username;

    public LeaderboardModel() {
    }

    public LeaderboardModel(String username, Integer score) {
        this.score = score;
        this.username = username;
    }

//    public String getScore() {
//        return score;
//    }

    //public void setScore(String score) {
//        this.score = score;
//    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
