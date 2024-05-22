package com.nyamutsambira.footballliveupdates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.Adapters.MatchEventsAdapter;
import com.nyamutsambira.footballliveupdates.DetailedLiveScoreActivity;
import com.nyamutsambira.footballliveupdates.FootballDataService;
import com.nyamutsambira.footballliveupdates.MatchEventsListener;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchEvents;
import com.nyamutsambira.footballliveupdates.R;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    private FootballDataService footballDataService;
    private List<MatchEvents> list;
    private String matchId;
    private LiveScores liveScore;
    private MatchEventsAdapter adapter;

    private RecyclerView recyclerView;
    private TextView dateTimeTv, refereeTv, stadiumTv, halfTimeTv, fullTimeTv, extraTimeTv, penaltyShootoutTv;
    private LinearLayout matchInfoLl, halfTimeLl, fullTimeLl, extraTimeLl, penaltyShootoutLl, matchEventsLl;

    public InfoFragment(LiveScores liveScore){
        this.liveScore = liveScore;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container, false);


        initViews(view);
        footballDataService = new FootballDataService(getActivity());
        list = new ArrayList<>();
        adapter = new MatchEventsAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);


        fetchMatchEvents();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.info_frag_recycler_view);
        dateTimeTv = view.findViewById(R.id.fragment_info_date_time_tv);
        refereeTv = view.findViewById(R.id.fragment_info_referee_tv);
        stadiumTv = view.findViewById(R.id.fragment_info_stadium_tv);
        halfTimeTv = view.findViewById(R.id.fragment_info_half_time_score_tv);
        fullTimeTv = view.findViewById(R.id.fragment_info_full_time_score_tv);
        extraTimeTv = view.findViewById(R.id.fragment_info_extra_time_score_tv);
        penaltyShootoutTv = view.findViewById(R.id.fragment_info_penalty_shootout_score_tv);
        matchInfoLl = view.findViewById(R.id.match_info_ll);
        halfTimeLl = view.findViewById(R.id.fragment_info_half_time_score_ll);
        fullTimeLl = view.findViewById(R.id.fragment_info_full_time_score_ll);
        extraTimeLl = view.findViewById(R.id.fragment_info_extra_time_score_ll);
        penaltyShootoutLl = view.findViewById(R.id.fragment_info_penalty_shootout_score_ll);
        matchEventsLl = view.findViewById(R.id.fragment_info_match_events);

        matchEventsLl.setVisibility(View.GONE);
        matchInfoLl.setVisibility(View.GONE);
        halfTimeLl.setVisibility(View.GONE);
        fullTimeLl.setVisibility(View.GONE);
        extraTimeLl.setVisibility(View.GONE);
        penaltyShootoutLl.setVisibility(View.GONE);

    }

    private void fetchMatchEvents()
    {
        footballDataService.getMatchEvents(Integer.toString(liveScore.getMatchId()), listener);
    }

    private MatchEventsListener listener = new MatchEventsListener() {
        @Override
        public void onResponse(List<MatchEvents> matchEventsList) {
            list.clear();
            list = matchEventsList;
            adapter.updateAdapter(list);
            loadBasicMatchInfo(liveScore);
            matchInfoLl.setVisibility(View.VISIBLE);
            matchEventsLl.setVisibility(View.VISIBLE);

            if(!liveScore.getScores().getHalfTimeScore().isEmpty())
            {
                halfTimeLl.setVisibility(View.VISIBLE);
            }

            if(!liveScore.getScores().getFullTimeScore().isEmpty())
            {
                fullTimeLl.setVisibility(View.VISIBLE);
            }

            if(!liveScore.getScores().getExtraTimeScore().isEmpty())
            {
                extraTimeLl.setVisibility(View.VISIBLE);
            }

            if(!liveScore.getScores().getPenaltyShootOutScore().isEmpty())
            {
                penaltyShootoutLl.setVisibility(View.VISIBLE);
            }

            //Toast.makeText(getActivity(), "Size of match events list: "  + list.size(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(VolleyError error) {
            //Toast.makeText(getActivity(), "There was error fetching match events", Toast.LENGTH_SHORT).show();
        }
    };

    private void loadBasicMatchInfo(LiveScores liveScore)
    {
        dateTimeTv.setText(liveScore.getScheduledTime());
        refereeTv.setText("");
        stadiumTv.setText(liveScore.getHomeTeam().getStadium());
        halfTimeTv.setText(liveScore.getScores().getHalfTimeScore());
        fullTimeTv.setText(liveScore.getScores().getFullTimeScore());
        extraTimeTv.setText(liveScore.getScores().getExtraTimeScore());
        penaltyShootoutTv.setText(liveScore.getScores().getPenaltyShootOutScore());
    }
}
