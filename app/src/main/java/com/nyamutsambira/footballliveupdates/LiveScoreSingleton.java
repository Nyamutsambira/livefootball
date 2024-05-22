package com.nyamutsambira.footballliveupdates;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class LiveScoreSingleton {
    private static LiveScoreSingleton myInstance;
    private static Context context;
    private RequestQueue requestQueue;

    private LiveScoreSingleton(Context cxt)
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

    public static synchronized LiveScoreSingleton getInstance(Context cxt)
    {
        if (myInstance == null)
        {
            myInstance = new LiveScoreSingleton(cxt);
        }

        return myInstance;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
