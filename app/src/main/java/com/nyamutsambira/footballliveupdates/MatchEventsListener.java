package com.nyamutsambira.footballliveupdates;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchEvents;

import java.util.List;

public interface MatchEventsListener {
    public void onResponse(List<MatchEvents> matchEventsList);
    public void onError(VolleyError error);
}
