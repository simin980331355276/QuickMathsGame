package com.example.quickmathsgame.Model;

public class LeaderboardModel {
    private String score;
    private String username;

    public LeaderboardModel() {
    }

    public LeaderboardModel(String score, String username) {
        this.score = score;
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
