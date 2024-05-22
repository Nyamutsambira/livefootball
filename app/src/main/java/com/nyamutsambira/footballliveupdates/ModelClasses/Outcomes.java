package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Outcomes implements Serializable {
    private String halfTime, fullTime, extraTime, penaltyShootOut;

    public Outcomes(String halfTime, String fullTime, String extraTime, String penaltyShootOut) {
        this.halfTime = halfTime;
        this.fullTime = fullTime;
        this.extraTime = extraTime;
        this.penaltyShootOut = penaltyShootOut;
    }

    public String getHalfTime() {
        return halfTime;
    }

    public String getFullTime() {
        return fullTime;
    }

    public String getExtraTime() {
        return extraTime;
    }

    public String getPenaltyShootOut() {
        return penaltyShootOut;
    }

    @Override
    public String toString() {
        return "Outcomes{" +
                "halfTime='" + halfTime + '\'' +
                ", fullTime='" + fullTime + '\'' +
                ", extraTime='" + extraTime + '\'' +
                ", penaltyShootOut='" + penaltyShootOut + '\'' +
                '}';
    }
}
