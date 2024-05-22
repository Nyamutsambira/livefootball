package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class PreGameOdds implements Serializable {
    private String homeWin, awayWin, draw;

    public PreGameOdds(String homeWin, String awayWin, String draw) {
        this.homeWin = homeWin;
        this.awayWin = awayWin;
        this.draw = draw;
    }

    public String getHomeWin() {
        return homeWin;
    }

    public String getAwayWin() {
        return awayWin;
    }

    public String getDraw() {
        return draw;
    }

    @Override
    public String toString() {
        return "PreGameOdds{" +
                "homeWin='" + homeWin + '\'' +
                ", awayWin='" + awayWin + '\'' +
                ", draw='" + draw + '\'' +
                '}';
    }
}
