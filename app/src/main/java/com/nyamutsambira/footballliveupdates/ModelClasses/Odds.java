package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Odds implements Serializable {
    private PreGameOdds preGameOdds;
    private LiveGameOdds liveGameOdds;

    public Odds(PreGameOdds preGameOdds, LiveGameOdds liveGameOdds) {
        this.preGameOdds = preGameOdds;
        this.liveGameOdds = liveGameOdds;
    }

    public PreGameOdds getPreGameOdds() {
        return preGameOdds;
    }

    public LiveGameOdds getLiveGameOdds() {
        return liveGameOdds;
    }

    @Override
    public String toString() {
        return "Outcomes{" +
                "preGameOdds=" + preGameOdds +
                ", liveGameOdds=" + liveGameOdds +
                '}';
    }
}
