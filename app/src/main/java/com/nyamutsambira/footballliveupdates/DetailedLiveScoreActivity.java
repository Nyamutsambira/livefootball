package com.nyamutsambira.footballliveupdates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.Fragments.H2HFragment;
import com.nyamutsambira.footballliveupdates.Fragments.InfoFragment;
import com.nyamutsambira.footballliveupdates.Fragments.LineUpsFragment;
import com.nyamutsambira.footballliveupdates.Fragments.OddsFragment;
import com.nyamutsambira.footballliveupdates.Fragments.PredictionsFragment;
import com.nyamutsambira.footballliveupdates.ModelClasses.AwayTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.Competition;
import com.nyamutsambira.footballliveupdates.ModelClasses.Country;
import com.nyamutsambira.footballliveupdates.ModelClasses.HomeTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchEvents;
import com.nyamutsambira.footballliveupdates.ModelClasses.Scores;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailedLiveScoreActivity extends AppCompatActivity {

    private ImageView backBtn, countryLogoIv, homeTeamLogoIv, awayTeamLogoIv;
    private TextView countryNameTv, leagueNameTv, homeTeamNameTv, awayTeamNameTv, scoreLineTv, timePlayedTv;
    private CardView infoCv, lineUpsCv, headToHeadCv, oddsCv, predictionsCv;
    private FrameLayout frame;

    private LiveScores liveScoreObj;
    private FootballDataService footballDataService;
    private List<MatchEvents> list;
    private String matchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_live_score);

        Intent i = getIntent();
        liveScoreObj = (LiveScores) i.getSerializableExtra("LIVE_SCORE_OBJ");

        footballDataService = new FootballDataService(DetailedLiveScoreActivity.this);
        list = new ArrayList<>();

        initViews();


        matchId = Integer.toString(liveScoreObj.getMatchId());
        //fetchMatchEvents();

        loadData(liveScoreObj);

        setBackgroundColorOfCardViews(infoCv);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        infoCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundColorOfCardViews(infoCv);
            }
        });

        lineUpsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundColorOfCardViews(lineUpsCv);
            }
        });

        headToHeadCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundColorOfCardViews(headToHeadCv);
            }
        });

        oddsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundColorOfCardViews(oddsCv);
            }
        });

        predictionsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundColorOfCardViews(predictionsCv);
            }
        });
    }

    private void setBackgroundColorOfCardViews(CardView cv) {
        int lightGrayColor = ContextCompat.getColor(DetailedLiveScoreActivity.this, R.color.lightGray);
        int greenColor = ContextCompat.getColor(DetailedLiveScoreActivity.this, R.color.green);

        if(cv.getId() == lineUpsCv.getId())
        {
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new LineUpsFragment()).commit();
            lineUpsCv.setCardBackgroundColor(greenColor);
            infoCv.setCardBackgroundColor(lightGrayColor);
            headToHeadCv.setCardBackgroundColor(lightGrayColor);
            oddsCv.setCardBackgroundColor(lightGrayColor);
            predictionsCv.setCardBackgroundColor(lightGrayColor);

        }
        else if(cv.getId() == headToHeadCv.getId())
        {
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new H2HFragment()).commit();
            lineUpsCv.setCardBackgroundColor(lightGrayColor);
            infoCv.setCardBackgroundColor(lightGrayColor);
            headToHeadCv.setCardBackgroundColor(greenColor);
            oddsCv.setCardBackgroundColor(lightGrayColor);
            predictionsCv.setCardBackgroundColor(lightGrayColor);
        }
        else if(cv.getId() == oddsCv.getId())
        {
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new OddsFragment()).commit();
            lineUpsCv.setCardBackgroundColor(lightGrayColor);
            infoCv.setCardBackgroundColor(lightGrayColor);
            headToHeadCv.setCardBackgroundColor(lightGrayColor);
            oddsCv.setCardBackgroundColor(greenColor);
            predictionsCv.setCardBackgroundColor(lightGrayColor);
        }
        else if(cv.getId() == predictionsCv.getId())
        {
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new PredictionsFragment()).commit();
            lineUpsCv.setCardBackgroundColor(lightGrayColor);
            infoCv.setCardBackgroundColor(lightGrayColor);
            headToHeadCv.setCardBackgroundColor(lightGrayColor);
            oddsCv.setCardBackgroundColor(lightGrayColor);
            predictionsCv.setCardBackgroundColor(greenColor);
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new InfoFragment(liveScoreObj)).commit();
            lineUpsCv.setCardBackgroundColor(lightGrayColor);
            infoCv.setCardBackgroundColor(greenColor);
            headToHeadCv.setCardBackgroundColor(lightGrayColor);
            oddsCv.setCardBackgroundColor(lightGrayColor);
            predictionsCv.setCardBackgroundColor(lightGrayColor);
        }
    }

    private void fetchMatchEvents()
    {
        footballDataService.getMatchEvents(matchId, listener);
    }

    private void loadData(LiveScores obj){
        try {
            Country country = liveScoreObj.getCountry();
            if(country != null)
                countryNameTv.setText(country.getName() + ": ");
            else
                countryNameTv.setVisibility(View.GONE);

            Competition competition = liveScoreObj.getCompetition();
            if(competition!= null)
                leagueNameTv.setText(competition.getName());
            else
                leagueNameTv.setVisibility(View.GONE);

            Scores scores = liveScoreObj.getScores();
            if(scores != null)
                scoreLineTv.setText(scores.getScore());
            else
                scoreLineTv.setText("? - ?");

            HomeTeam homeTeam = liveScoreObj.getHomeTeam();
            if(homeTeam != null)
                homeTeamNameTv.setText(homeTeam.getName());
            else
                homeTeamNameTv.setText("Home Team");

            AwayTeam awayTeam = liveScoreObj.getAwayTeam();
            if(awayTeam != null)
                awayTeamNameTv.setText(awayTeam.getName());
            else
                awayTeamNameTv.setText("Away Team");

            String time = liveScoreObj.getTime();
            timePlayedTv.setText(time + "'");
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private MatchEventsListener listener = new MatchEventsListener() {
        @Override
        public void onResponse(List<MatchEvents> matchEventsList) {
            list.clear();
            list = matchEventsList;
            Toast.makeText(DetailedLiveScoreActivity.this, "Size of match events list: "  + list.size(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(VolleyError error) {
            Toast.makeText(DetailedLiveScoreActivity.this, "There was error fetching match events", Toast.LENGTH_SHORT).show();
        }
    };

    private void initViews() {
        backBtn = findViewById(R.id.detailed_live_score_back_btn);
        countryLogoIv = findViewById(R.id.detailed_live_score_country_logo);
        countryNameTv = findViewById(R.id.detailed_live_score_country_name);
        leagueNameTv = findViewById(R.id.detailed_live_score_league_name);
        homeTeamLogoIv = findViewById(R.id.detailed_live_score_homeTeamIv);
        awayTeamLogoIv = findViewById(R.id.detailed_live_score_awayTeamIv);
        awayTeamNameTv = findViewById(R.id.detailed_live_score_awayTeamTv);
        homeTeamNameTv = findViewById(R.id.detailed_live_score_homeTeamTv);
        scoreLineTv = findViewById(R.id.detailed_live_score_scorelineTv);
        timePlayedTv = findViewById(R.id.detailed_live_score_minutes_played);
        infoCv = findViewById(R.id.detailed_live_score_info_cv);
        lineUpsCv = findViewById(R.id.detailed_live_score_line_ups_cv);
        headToHeadCv = findViewById(R.id.detailed_live_score_head_to_head_cv);
        oddsCv = findViewById(R.id.detailed_live_score_odds_cv);
        predictionsCv = findViewById(R.id.detailed_live_score_predictions_cv);
        frame = findViewById(R.id.detailed_activity_container);

    }
}