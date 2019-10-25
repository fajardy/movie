package com.fajar.movie.Movies;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fajar.movie.Movies.Adapter.Movie_Adapter_Viewpager;
import com.fajar.movie.Movies.Fragment.Movie_Now_Playing;
import com.fajar.movie.Movies.Fragment.Movie_Popular;
import com.fajar.movie.Movies.Fragment.Movie_Top_Rated;
import com.fajar.movie.Movies.Fragment.Movie_Upcoming;
import com.fajar.movie.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_Movies extends Fragment {

    View myfragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    ProgressBar pb;
    TextView tx_progess;


    public Frag_Movies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myfragment = inflater.inflate(R.layout.fragment_frag__movies, container, false);
        viewPager = myfragment.findViewById(R.id.view_pager_movie);
        tabLayout = myfragment.findViewById(R.id.tab_layout_movie);
        tx_progess = myfragment.findViewById(R.id.persentase);
        pb = myfragment.findViewById(R.id.progress);
        new Loading(0).execute();
        return myfragment;
    }

    public class Loading extends AsyncTask<Void, Integer,Integer>{
        Integer value=0;
        Integer end=0;

        public Loading(Integer value) {
            this.value = end;
            this.end = value;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            tx_progess.setVisibility(View.VISIBLE);
            pb.setMax(100);
            tx_progess.setText("0%");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
            String s = String.valueOf(values[0]);
            tx_progess.setText(s+"%");
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for (int i = value;i<end;i++) {
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (end == 100) {
                this.end=0;
                this.value=0;
                pb.setProgress(0);
                pb.setVisibility(View.INVISIBLE);
                tx_progess.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupviewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupviewpager(ViewPager viewPager) {
        Movie_Adapter_Viewpager adapter_viewpager_movie = new Movie_Adapter_Viewpager(getChildFragmentManager());
        adapter_viewpager_movie.addfragment(new Movie_Popular(), "Popular");
        adapter_viewpager_movie.addfragment(new Movie_Top_Rated(), "Top Rated");
        adapter_viewpager_movie.addfragment(new Movie_Upcoming(), "Upcoming");
        adapter_viewpager_movie.addfragment(new Movie_Now_Playing(), "Now Playing");
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter_viewpager_movie);
    }
}
