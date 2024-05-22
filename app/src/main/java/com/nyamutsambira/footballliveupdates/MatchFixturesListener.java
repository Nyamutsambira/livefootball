package com.nyamutsambira.footballliveupdates;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchFixtures;

import java.util.List;

public interface MatchFixturesListener {
    public void onResponse(List<MatchFixtures> matchFixturesList);
    public void onError(VolleyError error);
}
