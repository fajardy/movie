package com.fajar.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.fajar.movie.Movies.Frag_Movies;
import com.fajar.movie.People.Frag_People;
import com.fajar.movie.Tv.Frag_Tv;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navselected);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag_Movies()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navselected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedfrag = null;
            switch (menuItem.getItemId()){
                case R.id.nav_movie:
                    selectedfrag = new Frag_Movies();
                    break;
            }
            switch (menuItem.getItemId()){
                case R.id.nav_tv:
                    selectedfrag = new Frag_Tv();
                    break;
            }
            switch (menuItem.getItemId()){
                case R.id.nav_people:
                    selectedfrag = new Frag_People();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedfrag).commit();
            return true;
        }
    };
}
