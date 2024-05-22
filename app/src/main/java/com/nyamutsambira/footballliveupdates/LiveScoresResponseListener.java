package com.nyamutsambira.footballliveupdates;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;

import org.json.JSONArray;

import java.util.List;

public interface LiveScoresResponseListener {
    void onResponse(List<LiveScores> liveScoresList);
    void onError(VolleyError error);
}
