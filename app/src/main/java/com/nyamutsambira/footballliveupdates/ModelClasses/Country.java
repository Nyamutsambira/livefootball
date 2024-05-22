package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Country implements Serializable {
    private int id;
    private String fifaCode;
    private String name;

    public Country(int id, String fifaCode, String name) {
        this.id = id;
        this.fifaCode = fifaCode;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getFifaCode() {
        return fifaCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", fifaCode='" + fifaCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
