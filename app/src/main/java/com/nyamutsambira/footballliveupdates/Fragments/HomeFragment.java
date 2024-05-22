package com.nyamutsambira.footballliveupdates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.Adapters.LiveScoresAdapter;
import com.nyamutsambira.footballliveupdates.FootballDataService;
import com.nyamutsambira.footballliveupdates.LiveScoresResponseListener;
import com.nyamutsambira.footballliveupdates.ModelClasses.LiveScores;
import com.nyamutsambira.footballliveupdates.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private RecyclerView recyclerView;

    private List<LiveScores> list;
    private LiveScoresAdapter adapter;
    private FootballDataService footballDataService;

    public HomeFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);

        list = new ArrayList<>();
        adapter = new LiveScoresAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);

        footballDataService = new FootballDataService(getActivity());

        fetchLiveScores();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.home_frag_recycler_view);
    }

    private void fetchLiveScores(){
        //In this method we fetch data from LiveScores API through our FootballDataService class

        footballDataService.getLiveScores(listener);
    }

    private LiveScoresResponseListener listener = new LiveScoresResponseListener() {
        @Override
        public void onResponse(List<LiveScores> liveScoresList) {
            list = liveScoresList;

            if(!list.isEmpty())
            {
                adapter.updateAdapter(list);
                //Toast.makeText(getActivity(), "(Home Fragment)Size of list: " + list.size(), Toast.LENGTH_SHORT).show();
            }
            else{}
                //Toast.makeText(getActivity(), "There are no live matches at the moment... ", Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onError(VolleyError error) {
            //Toast.makeText(getActivity(), "There was error fetching live games. Try again later...", Toast.LENGTH_SHORT).show();
        }
    };

}
