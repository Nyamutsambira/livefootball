package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Scores implements Serializable {
    private String score, halfTimeScore, fullTimeScore, extraTimeScore, penaltyShootOutScore;

    public Scores(String score, String halfTimeScore, String fullTimeScore, String extraTimeScore, String penaltyShootOutScore) {
        this.score = score;
        this.halfTimeScore = halfTimeScore;
        this.fullTimeScore = fullTimeScore;
        this.extraTimeScore = extraTimeScore;
        this.penaltyShootOutScore = penaltyShootOutScore;
    }

    public String getScore() {
        return score;
    }

    public String getHalfTimeScore() {
        return halfTimeScore;
    }

    public String getFullTimeScore() {
        return fullTimeScore;
    }

    public String getExtraTimeScore() {
        return extraTimeScore;
    }

    public String getPenaltyShootOutScore() {
        return penaltyShootOutScore;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "score='" + score + '\'' +
                ", halfTimeScore='" + halfTimeScore + '\'' +
                ", fullTimeScore='" + fullTimeScore + '\'' +
                ", extraTimeScore='" + extraTimeScore + '\'' +
                ", penaltyShootOutScore='" + penaltyShootOutScore + '\'' +
                '}';
    }
}
