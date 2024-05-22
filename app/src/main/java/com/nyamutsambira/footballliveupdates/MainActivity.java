package com.nyamutsambira.footballliveupdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nyamutsambira.footballliveupdates.Fragments.FixturesFragment;
import com.nyamutsambira.footballliveupdates.Fragments.HomeFragment;
import com.nyamutsambira.footballliveupdates.Fragments.NewsFragment;

public class MainActivity extends AppCompatActivity{

    private FrameLayout frame;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            initViews();
            getSupportFragmentManager().beginTransaction().replace(frame.getId(), new HomeFragment()).commit();
            bottomNav.setOnNavigationItemSelectedListener(listener);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment;
            int itemId = item.getItemId();

            switch (itemId)
            {
                case R.id.menu_fixtures: selectedFragment = new FixturesFragment(); break;
                case R.id.menu_news: selectedFragment = new NewsFragment();break;
                default: selectedFragment = new HomeFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(frame.getId(), selectedFragment).commit();
            return false;
        }
    };

    private void initViews(){
        frame = findViewById(R.id.fragment_container);
        bottomNav = findViewById(R.id.bottom_navigation);
    }
}