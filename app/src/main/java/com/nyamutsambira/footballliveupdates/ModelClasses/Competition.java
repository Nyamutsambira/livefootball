package com.nyamutsambira.footballliveupdates.ModelClasses;

import java.io.Serializable;

public class Competition implements Serializable {
    private int id;
    private String name;

    public Competition(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
