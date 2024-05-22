package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class MatchEvents implements Serializable {

    private String id;
    private String matchId;
    private String player;
    private String time;
    private String event;
    private String sort;
    private String homeOrAway;


    public MatchEvents(String id, String matchId, String player, String time, String event,
                       String sort, String homeOrAway) {
        this.id = id;
        this.matchId = matchId;
        this.player = player;
        this.time = time;
        this.event = event;
        this.sort = sort;
        this.homeOrAway = homeOrAway;

    }

    public String getId() {
        return id;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getPlayer() {
        return player;
    }

    public String getTime() {
        return time;
    }

    public String getEvent() {
        return event;
    }

    public String getSort() {
        return sort;
    }

    public String getHomeOrAway() {
        return homeOrAway;
    }

    @Override
    public String toString() {
        return "MatchEvents{" +
                "id='" + id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", player='" + player + '\'' +
                ", time='" + time + '\'' +
                ", event='" + event + '\'' +
                ", sort='" + sort + '\'' +
                ", homeOrAway='" + homeOrAway + '\'' +
                '}';
    }
}
