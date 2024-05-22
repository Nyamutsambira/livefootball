package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class LiveScores implements Serializable {

    private int matchId;
    private String scheduledTime;
    private String time;
    private int fixtureId;
    private String status;
    private HomeTeam homeTeam;
    private Country country;
    private String location;
    private AwayTeam awayTeam;
    private Competition competition;
    private Odds odds;
    private Outcomes outcomes;
    private Scores scores;
    private Urls urls;

    public LiveScores(){}

    public LiveScores(int matchId, String scheduledTime, String time, int fixtureId, String status,
                      HomeTeam homeTeam, Country country, String location, AwayTeam awayTeam,
                      Competition competition, Odds odds, Outcomes outcomes, Scores scores, Urls urls) {
        this.matchId = matchId;
        this.scheduledTime = scheduledTime;
        this.time = time;
        this.fixtureId = fixtureId;
        this.status = status;
        this.homeTeam = homeTeam;
        this.country = country;
        this.location = location;
        this.awayTeam = awayTeam;
        this.competition = competition;
        this.odds = odds;
        this.outcomes = outcomes;
        this.scores = scores;
        this.urls = urls;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public String getTime() {
        return time;
    }

    public int getFixtureId() {
        return fixtureId;
    }

    public String getStatus() {
        return status;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public Country getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Odds getOdds() {
        return odds;
    }

    public Outcomes getOutcomes() {
        return outcomes;
    }

    public Scores getScores() {
        return scores;
    }

    public Urls getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return "LiveScores{" +
                "matchId=" + matchId +
                ", scheduledTime='" + scheduledTime + '\'' +
                ", time='" + time + '\'' +
                ", fixtureId=" + fixtureId +
                ", status='" + status + '\'' +
                ", homeTeam=" + homeTeam +
                ", country=" + country +
                ", location='" + location + '\'' +
                ", awayTeam=" + awayTeam +
                ", competition=" + competition +
                ", odds=" + odds +
                ", outcomes=" + outcomes +
                ", scores=" + scores +
                ", urls=" + urls +
                '}';
    }
}