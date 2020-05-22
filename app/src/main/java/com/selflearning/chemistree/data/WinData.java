package com.selflearning.chemistree.data;

public class WinData {

    private long timeInMillis;
    private long score;

    public WinData(long timeInMillis, long score) {
        this.timeInMillis = timeInMillis;
        this.score = score;
    }
    public WinData(){}

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
}
