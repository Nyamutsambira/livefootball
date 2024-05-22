package com.nyamutsambira.footballliveupdates.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.nyamutsambira.footballliveupdates.Adapters.MatchFixturesAdapter;
import com.nyamutsambira.footballliveupdates.MatchFixturesListener;
import com.nyamutsambira.footballliveupdates.FootballDataService;
import com.nyamutsambira.footballliveupdates.ModelClasses.MatchFixtures;
import com.nyamutsambira.footballliveupdates.R;

import java.util.ArrayList;
import java.util.List;

public class FixturesFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<MatchFixtures> list;
    private MatchFixturesAdapter adapter;
    private FootballDataService footballDataService;

    public FixturesFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);

        initViews(view);

        list = new ArrayList<>();
        adapter = new MatchFixturesAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        footballDataService = new FootballDataService(getActivity());

        fetchMatchFixtures();

        return view;
    }

    private void fetchMatchFixtures() {
        footballDataService.getFixtures( listener);
    }

    private MatchFixturesListener listener = new MatchFixturesListener() {
        @Override
        public void onResponse(List<MatchFixtures> matchFixturesList) {
            list = matchFixturesList;
            adapter.updateAdapter(list);
            //Toast.makeText( getActivity(), "Size of list: " + list.size(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(VolleyError error) {
            //Toast.makeText(getActivity()., "There was error retrieving match fixtures...", Toast.LENGTH_SHORT).show();
        }
    };

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.fragment_fixtures_recycler_view);
    }
}
