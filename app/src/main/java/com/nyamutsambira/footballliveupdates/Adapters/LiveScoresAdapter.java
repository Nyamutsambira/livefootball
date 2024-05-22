package com.nyamutsambira.footballliveupdates.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyamutsambira.footballliveupdates.DetailedLiveScoreActivity;
import com.nyamutsambira.footballliveupdates.ModelClasses.AwayTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.Competition;
import com.nyamutsambira.footballliveupdates.ModelClasses.Country;
import com.nyamutsambira.footballliveupdates.ModelClasses.HomeTeam;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;
import com.nyamutsambira.footballliveupdates.ModelClasses.Scores;
import com.nyamutsambira.footballliveupdates.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LiveScoresAdapter extends RecyclerView.Adapter<LiveScoresAdapter.ViewHolder> {

    private Context context;
    private List<LiveScores> livesScoresList;

    public LiveScoresAdapter(Context context, List<LiveScores> livesScoresList) {
        this.context = context;
        this.livesScoresList = livesScoresList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.live_score_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        LiveScores liveScoreObj = livesScoresList.get(i);

        Country country = liveScoreObj.getCountry();
        if(country != null)
            holder.countryNameTv.setText(country.getName() + ": ");
        else
            holder.countryNameTv.setVisibility(View.GONE);

        Competition competition = liveScoreObj.getCompetition();
        if(competition!= null)
            holder.leagueNameTv.setText(competition.getName());
        else
            holder.leagueNameTv.setVisibility(View.GONE);

        Scores scores = liveScoreObj.getScores();
        if(scores != null)
            holder.scorelineTv.setText(scores.getScore());
        else
            holder.scorelineTv.setText("? - ?");

        HomeTeam homeTeam = liveScoreObj.getHomeTeam();
        if(homeTeam != null)
            holder.homeTeamNameTv.setText(homeTeam.getName());
        else
            holder.homeTeamNameTv.setText("Home Team");

        AwayTeam awayTeam = liveScoreObj.getAwayTeam();
        if(awayTeam != null)
            holder.awayTeamNameTv.setText(awayTeam.getName());
        else
            holder.awayTeamNameTv.setText("Away Team");

        String time = liveScoreObj.getTime();
        holder.minutesPlayedTv.setText(time + "'");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailedLiveScoreActivity(liveScoreObj);
            }
        });

    }

    private void openDetailedLiveScoreActivity(LiveScores liveScoreObj) {
        Intent intent = new Intent(context, DetailedLiveScoreActivity.class);
        intent.putExtra("LIVE_SCORE_OBJ", liveScoreObj);
        context.startActivity(intent);
    }

    public void updateAdapter(List<LiveScores> list){
        this.livesScoresList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return livesScoresList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView countryLogoIv, homeTeamLogoIv, awayTeamLogoIv;
        private TextView countryNameTv, leagueNameTv, minutesPlayedTv, homeTeamNameTv, awayTeamNameTv, scorelineTv;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            countryLogoIv = itemView.findViewById(R.id.country_logo);
            homeTeamLogoIv = itemView.findViewById(R.id.homeTeamIv);
            awayTeamLogoIv = itemView.findViewById(R.id.awayTeamIv);
            countryNameTv = itemView.findViewById(R.id.country_name);
            leagueNameTv = itemView.findViewById(R.id.league_name);
            minutesPlayedTv = itemView.findViewById(R.id.minutes_played);
            homeTeamNameTv = itemView.findViewById(R.id.homeTeamTv);
            awayTeamNameTv = itemView.findViewById(R.id.awayTeamTv);
            scorelineTv = itemView.findViewById(R.id.scorelineTv);


        }
    }
}
