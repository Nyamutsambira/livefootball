package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class HomeTeam implements Serializable {
    private int id;
    private int countryId;
    private String name;
    private String stadium;

    public HomeTeam(int id, int countryId, String name, String stadium) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
        this.stadium = stadium;
    }

    public int getId() {
        return id;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public String getStadium() {
        return stadium;
    }

    @Override
    public String toString() {
        return "HomeTeam{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", name='" + name + '\'' +
                ", stadium='" + stadium + '\'' +
                '}';
    }
}
