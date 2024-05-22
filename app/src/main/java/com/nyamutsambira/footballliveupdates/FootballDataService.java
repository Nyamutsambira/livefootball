package com.nyamutsambira.footballliveupdates;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nyamutsambira.footballliveupdates.ModelClasses.AwayTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.Competition;
import com.nyamutsambira.footballliveupdates.ModelClasses.Country;
import com.nyamutsambira.footballliveupdates.ModelClasses.HomeTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveGameOdds;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchEvents;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchFixtures;
import com.nyamutsambira.footballliveupdates.ModelClasses.Odds;
import com.nyamutsambira.footballliveupdates.ModelClasses.Outcomes;
import com.nyamutsambira.footballliveupdates.ModelClasses.PreGameOdds;
import com.nyamutsambira.footballliveupdates.ModelClasses.Scores;
import com.nyamutsambira.footballliveupdates.ModelClasses.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FootballDataService {

    //We use the services of Livescores API
    private static final String KEY = "SRi19400B3BoctEp";
    private static final String SECRET = "SSrr4pGtlohy41dWSvX2zX3b1Eze7eD0";
    //private static String urlLiveScores = "https://livescore-api.com/api-client/matches/live.json?&key="+KEY+"&secret="+SECRET;
    private Context context;

    private List<LiveScores> liveScoresList;
    private List<MatchEvents> matchEventsList;
    private List<MatchFixtures> matchFixturesList;

    public FootballDataService(Context context)
    {
        this.context = context;
    }

    public void getFixtures(MatchFixturesListener listener) {
        String fixturesUrl = "https://livescore-api.com/api-client/fixtures/matches.json?&key="+ KEY
                + "&secret=" + SECRET;
        matchFixturesList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                fixturesUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean isSuccessful = response.getBoolean("success");
                            if(isSuccessful)
                            {
                                JSONObject dataObj = response.getJSONObject("data");
                                JSONArray fixturesArray = dataObj.getJSONArray("fixtures");

                                int sizeOfFixturesArray = fixturesArray.length();
                                if(sizeOfFixturesArray > 0)
                                {
                                    for(int i = 0; i < sizeOfFixturesArray; i++)
                                    {
                                        JSONObject obj = fixturesArray.getJSONObject(i);

                                        int fixtureId = obj.getInt("id");
                                        Competition competition = getCompetition(obj);
                                        String time = obj.getString("time");
                                        String date = obj.getString("date");
                                        String homeName = obj.getString("home_name");
                                        String awayName = obj.getString("away_name");
                                        int homeId = obj.getInt("home_id");
                                        int awayId = obj.getInt("away_id");
                                        String location = obj.getString("location");
                                        String headToHead = obj.getString("h2h");
                                        Odds odds = getOdds(obj);

                                        MatchFixtures matchFixture = new MatchFixtures(fixtureId,
                                                competition, time, date, homeName, awayName, homeId,
                                                awayId, location, headToHead, odds);

                                        matchFixturesList.add(matchFixture);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finally {
                            listener.onResponse(matchFixturesList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                        Toast.makeText(context, "There was error fetching match fixtures. Try again later...", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        MatchEventsSingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getMatchEvents(String matchId, MatchEventsListener listener){
        String matchEventsUrl = "https://livescore-api.com/api-client/scores/events.json?id="+matchId+"&key="+KEY+"&secret="+SECRET;
        matchEventsList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                matchEventsUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean isSuccessful = response.getBoolean("success");

                            if(isSuccessful)
                            {
                                JSONObject dataObj = response.getJSONObject("data");
                                JSONObject matchObj = dataObj.getJSONObject("match");
                                String score = matchObj.getString("score");
                                String halfTimeScore = matchObj.getString("ht_score");
                                String fullTimeScore = matchObj.getString("ft_score");
                                String extraTimeScore = matchObj.getString("et_score");
                                String penaltyShootoutScore = matchObj.getString("ps_score");
                                String time = matchObj.getString("time");
                                String status = matchObj.getString("status");
                                JSONArray eventsArray = dataObj.getJSONArray("event");

                                if(eventsArray.length() > 0)
                                    //Toast.makeText(context, "Size of array :" + eventsArray.length(), Toast.LENGTH_SHORT).show();
                                {
                                    int i = eventsArray.length() -1;
                                    while(i >= 0)
                                    {
                                        JSONObject eventJSonObj = eventsArray.getJSONObject(i);
                                        String id = eventJSonObj.getString("id");
                                        String matchId = eventJSonObj.getString("match_id");
                                        String player = eventJSonObj.getString("player");
                                        String eventTime = eventJSonObj.getString("time");
                                        String event = eventJSonObj.getString("event");
                                        String sort = eventJSonObj.getString("sort");
                                        String homeOrAway = eventJSonObj.getString("home_away");

                                        MatchEvents matchEvent = new MatchEvents(id, matchId, player, eventTime, event, sort, homeOrAway);
                                        matchEventsList.add(matchEvent);
                                        i--;
                                    }
                                }


                            }
                        } catch (JSONException e) {
                            //e.printStackTrace();
                            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finally {
                            listener.onResponse(matchEventsList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                        Toast.makeText(context, "There was error fetching match events. Try again later...", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        MatchEventsSingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getLiveScores(LiveScoresResponseListener listener){
        String urlLiveScores = "https://livescore-api.com/api-client/matches/live.json?&key="+KEY+"&secret="+SECRET;
        this.liveScoresList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                urlLiveScores,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        liveScoresList.clear();
                        try {
                            boolean isSuccessful = response.getBoolean("success");
                            if(isSuccessful)
                            {
                                JSONObject dataObj = response.getJSONObject("data");
                                JSONArray matchArr = dataObj.getJSONArray("match");
                                //Toast.makeText(context, "Size of match array: " + matchArr.length(), Toast.LENGTH_SHORT).show();
                                if(matchArr.length() > 0)
                                {
                                    //Toast.makeText(context, "Inside if statement", Toast.LENGTH_SHORT).show();
                                    for(int i = 0; i < matchArr.length(); i++)
                                    {
                                        //Toast.makeText(context, "Inside for statement", Toast.LENGTH_SHORT).show();
                                        JSONObject obj = matchArr.getJSONObject(i);

                                        int matchId = obj.getInt("id");
                                        String scheduledTime = obj.getString("scheduled");
                                        String time = obj.getString("time");
                                        int fixtureId = obj.getInt("fixture_id");
                                        String status = obj.getString("status");
                                        HomeTeam homeTeam = getHomeTeam(obj);
                                        Country country = getCountry(obj);
                                        String location = obj.getString("location");
                                        AwayTeam awayTeam = getAwayTeam(obj);
                                        Competition competition = getCompetition(obj);
                                        Odds odds = getOdds(obj);
                                        Outcomes outcomes = getOutcomes(obj);
                                        Scores scores = getScores(obj);
                                        Urls urls = getUrls(obj);

                                        LiveScores liveScore = new LiveScores(matchId, scheduledTime,
                                                time, fixtureId, status, homeTeam, country, location,
                                                awayTeam, competition, odds, outcomes, scores, urls);

                                        liveScoresList.add(liveScore);

                                    }
                                }
                            }


                        } catch (JSONException e) {
                            //e.printStackTrace();
                            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finally{
                            listener.onResponse(liveScoresList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                        Toast.makeText(context, "There was error fetching live games. Try again later...", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        LiveScoreSingleton.getInstance(context).addToRequestQueue(request);
    }

    private Urls getUrls(JSONObject obj) throws JSONException{
        Urls urls = null;

        /*
        JSONObject urlsJSonObj = obj.getJSONObject("urls");

        if(urlsJSonObj != null)
        {
            String events = urlsJSonObj.getString("events");
            String statistics = urlsJSonObj.getString("statistics");
            String lineUps = urlsJSonObj.getString("line_ups");
            String headToHead = urlsJSonObj.getString("head2head");
            
            urls = new Urls(events, statistics, lineUps, headToHead);
        }

         */

        return urls;
    }

    private Scores getScores(JSONObject obj) throws JSONException{
        Scores scores = null;
        
        JSONObject scoresJSonObj = obj.getJSONObject("scores");

        if(scoresJSonObj != null)
        {
            String score = scoresJSonObj.getString("score");
            String halfTimeScore = scoresJSonObj.getString("ht_score");
            String fullTimeScore = scoresJSonObj.getString("ft_score");
            String extraTimeScore = scoresJSonObj.getString("et_score");
            String penaltyShootOutScore = scoresJSonObj.getString("ps_score");

            scores = new Scores(score, halfTimeScore, fullTimeScore, extraTimeScore, penaltyShootOutScore);

        }

        return scores;

    }

    private Outcomes getOutcomes(JSONObject obj) throws JSONException{
        Outcomes outcomes = null;

        JSONObject outcomesJSonObj = obj.getJSONObject("outcomes");

        if(outcomesJSonObj != null)
        {
            String halfTime = outcomesJSonObj.getString("half_time");
            String fullTime = outcomesJSonObj.getString("full_time");
            String extraTime = outcomesJSonObj.getString("extra_time");
            String penaltyShootout = outcomesJSonObj.getString("penalty_shootout");

            outcomes = new Outcomes(halfTime, fullTime, extraTime, penaltyShootout);
        }

        return outcomes;
    }

    private Odds getOdds(JSONObject obj) throws JSONException{
        Odds odds = null;

        JSONObject oddsJSonObj = obj.getJSONObject("odds");
        if(oddsJSonObj != null)
        {
            LiveGameOdds liveGameOdds = null;
            PreGameOdds preGameOdds = null;


            JSONObject liveOddsJSonObj = oddsJSonObj.getJSONObject("live");
            if(liveOddsJSonObj != null)
            {
                String homeWin = liveOddsJSonObj.getString("1");
                String awayWin = liveOddsJSonObj.getString("2");
                String draw = liveOddsJSonObj.getString("X");

                liveGameOdds = new LiveGameOdds(homeWin, awayWin, draw);
            }

            JSONObject preGameOddsJsonObj = oddsJSonObj.getJSONObject("pre");
            if(preGameOddsJsonObj != null)
            {
                String homeWin = preGameOddsJsonObj.getString("1");
                String awayWin = preGameOddsJsonObj.getString("2");
                String draw = preGameOddsJsonObj.getString("X");

                preGameOdds = new PreGameOdds(homeWin, awayWin, draw);

            }

            odds = new Odds(preGameOdds, liveGameOdds);

        }


        return odds;
    }

    private Competition getCompetition(JSONObject obj) throws JSONException{
        Competition competition = null;

        JSONObject competitionJSonObj = obj.getJSONObject("competition");
        if(competitionJSonObj != null)
        {
            int id = competitionJSonObj.getInt("id");
            String name = competitionJSonObj.getString("name");

            competition = new Competition(id, name);
        }

        return competition;
    }

    private AwayTeam getAwayTeam(JSONObject obj) throws  JSONException{
        AwayTeam awayTeam = null;

        JSONObject awayTeamJSonObj = obj.getJSONObject("away");
        if(awayTeamJSonObj != null)
        {
            int id = awayTeamJSonObj.getInt("id");
            int countryId = awayTeamJSonObj.getInt("country_id");
            String name = awayTeamJSonObj.getString("name");
            String stadium = awayTeamJSonObj.getString("stadium");

            awayTeam = new AwayTeam(id, countryId, name, stadium);
        }

        return awayTeam;
    }

    private Country getCountry(JSONObject obj) {
        Country country = null;

        try{
            JSONObject countryJsonObj = obj.getJSONObject("country");

            if(countryJsonObj != null) {
                int id = countryJsonObj.getInt("id");
                String fifaCode = countryJsonObj.getString("fifa_code");
                String name = countryJsonObj.getString("name");

                country = new Country(id, fifaCode, name);
            }
        }catch(JSONException e){
            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            return  country;
        }

    }

    private HomeTeam getHomeTeam(JSONObject obj) throws JSONException{
        HomeTeam homeTeam = null;

        JSONObject homeTeamJsonObj = obj.getJSONObject("home");
        if(homeTeamJsonObj != null)
        {
            int id = homeTeamJsonObj.getInt("id");
            int countryId = homeTeamJsonObj.getInt("country_id");
            String name = homeTeamJsonObj.getString("name");
            String stadium = homeTeamJsonObj.getString("stadium");

            homeTeam = new HomeTeam(id,countryId,name, stadium);
        }

         return homeTeam;
    }

}
