package com.fajar.movie.Tv;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fajar.movie.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_Tv extends Fragment {


    public Frag_Tv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__tv, container, false);
    }

}
