package com.nyamutsambira.footballliveupdates;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MatchEventsSingleton {
    private static MatchEventsSingleton myInstance;
    private static Context context;
    private RequestQueue requestQueue;

    private MatchEventsSingleton(Context cxt)
    {
        context = cxt;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return  requestQueue;
    }

    public static synchronized MatchEventsSingleton getInstance(Context cxt)
    {
        if (myInstance == null)
        {
            myInstance = new MatchEventsSingleton(cxt);
        }

        return myInstance;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
