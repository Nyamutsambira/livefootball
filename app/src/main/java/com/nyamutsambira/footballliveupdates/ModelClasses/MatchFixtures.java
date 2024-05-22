package com.nyamutsambira.footballliveupdates.ModelClasses;

public class MatchFixtures {

    private int fixtureId;
    private Competition competition;
    private String time;
    private String date;
    private String homeName;
    private String awayName;
    private int homeId;
    private int awayId;
    private String location;
    private String headToHead;
    private Odds odds;

    public MatchFixtures(int fixtureId, Competition competition, String time, String date,
                         String homeName, String awayName, int homeId, int awayId, String location,
                         String headToHead, Odds odds) {
        this.fixtureId = fixtureId;
        this.competition = competition;
        this.time = time;
        this.date = date;
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeId = homeId;
        this.awayId = awayId;
        this.location = location;
        this.headToHead = headToHead;
        this.odds = odds;
    }

    public int getFixtureId() {
        return fixtureId;
    }

    public Competition getCompetition() {
        return competition;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public int getHomeId() {
        return homeId;
    }

    public int getAwayId() {
        return awayId;
    }

    public String getLocation() {
        return location;
    }

    public String getHeadToHead() {
        return headToHead;
    }

    public Odds getOdds() {
        return odds;
    }

    @Override
    public String toString() {
        return "MatchFixtures{" +
                "fixtureId=" + fixtureId +
                ", competition=" + competition +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", homeName='" + homeName + '\'' +
                ", awayName='" + awayName + '\'' +
                ", homeId=" + homeId +
                ", awayId=" + awayId +
                ", location='" + location + '\'' +
                ", headToHead='" + headToHead + '\'' +
                ", odds=" + odds +
                '}';
    }
}
