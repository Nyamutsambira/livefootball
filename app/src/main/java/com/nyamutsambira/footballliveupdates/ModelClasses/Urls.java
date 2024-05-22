package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Urls implements Serializable {
    private String events, statistics, lineUps, headToHead;

    public Urls(String events, String statistics, String lineUps, String headToHead) {
        this.events = events;
        this.statistics = statistics;
        this.lineUps = lineUps;
        this.headToHead = headToHead;
    }

    public String getEvents() {
        return events;
    }

    public String getStatistics() {
        return statistics;
    }

    public String getLineUps() {
        return lineUps;
    }

    public String getHeadToHead() {
        return headToHead;
    }

    @Override
    public String toString() {
        return "Urls{" +
                "events='" + events + '\'' +
                ", statistics='" + statistics + '\'' +
                ", lineUps='" + lineUps + '\'' +
                ", headToHead='" + headToHead + '\'' +
                '}';
    }
}
