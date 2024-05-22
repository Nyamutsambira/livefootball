package com.nyamutsambira.footballliveupdates.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyamutsambira.footballliveupdates.ModelClasses.MatchFixtures;
import com.nyamutsambira.footballliveupdates.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MatchFixturesAdapter extends RecyclerView.Adapter<MatchFixturesAdapter.ViewHolder> {

    private Context context;
    private List<MatchFixtures> list;

    public MatchFixturesAdapter(Context context, List<MatchFixtures> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_fixtures_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        MatchFixtures fixture = list.get(i);
        holder.homeTeamTv.setText(fixture.getHomeName());
        holder.awayTeamTv.setText(fixture.getAwayName());

        String time = fixture.getTime();
        String date = fixture.getDate();
        try {
            Date inputTime = new SimpleDateFormat("hh:mm:ss").parse(time);
            String outputTime = new SimpleDateFormat("h:mm a").format(inputTime);
            holder.timeTv.setText(outputTime);

            Date inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String outputDate = new SimpleDateFormat("dd MMM, yyyy").format(inputDate);
            holder.dateTv.setText(outputDate);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.competitionTv.setText(fixture.getCompetition().getName());

        //set logos to teams later on
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAdapter(List<MatchFixtures> matchFixturesList){
        list = matchFixturesList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView homeTeamLogo, awayTeamLogo, competitionLogo;
        private TextView homeTeamTv, awayTeamTv, dateTv, timeTv, competitionTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            homeTeamLogo = itemView.findViewById(R.id.match_fixtures_layout_home_team_logo);
            awayTeamLogo = itemView.findViewById(R.id.match_fixtures_layout_away_team_logo);
            competitionLogo = itemView.findViewById(R.id.match_fixtures_layout_competition_logo);
            homeTeamTv = itemView.findViewById(R.id.match_fixtures_home_team_tv);
            awayTeamTv = itemView.findViewById(R.id.match_fixtures_away_team_tv);
            dateTv = itemView.findViewById(R.id.match_fixtures_date_tv);
            timeTv = itemView.findViewById(R.id.match_fixtures_time_tv);
            competitionTv = itemView.findViewById(R.id.match_fixtures_layout_competition_tv);
        }
    }
}
