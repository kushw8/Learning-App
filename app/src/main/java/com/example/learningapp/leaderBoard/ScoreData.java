package com.example.learningapp.leaderBoard;

public class ScoreData  {

    String name;
    long score;

    public ScoreData() {
    }

    public ScoreData(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
