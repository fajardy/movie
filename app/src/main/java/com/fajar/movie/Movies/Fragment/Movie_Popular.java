package com.fajar.movie.Movies.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fajar.movie.MainActivity;
import com.fajar.movie.Movies.Adapter.Movie_Adapter_Retrofit;
import com.fajar.movie.Api.ApiRequest;
import com.fajar.movie.Api.RetroServer;
import com.fajar.movie.Movies.Frag_Movies;
import com.fajar.movie.Movies.Model.ResponeModel;
import com.fajar.movie.Movies.Model.Result;
import com.fajar.movie.Movies.Model.TimeGenre.TimedanGenre;
import com.fajar.movie.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movie_Popular extends Fragment {

    View view;
    RecyclerView recyclerView;
    private Movie_Adapter_Retrofit madapter;
    SwipeRefreshLayout refreshLayout;
    List<Result> datalist;
    ProgressDialog pg;
    Frag_Movies fr;

    public Movie_Popular() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_movie__popular, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Recyclerview_movie_popular);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout_movie_popular);
        pg = new ProgressDialog(getActivity());
        datalist=new ArrayList<>();
        fr = ((Frag_Movies)Movie_Popular.this.getParentFragment());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        madapter = new Movie_Adapter_Retrofit(getActivity(),datalist);
        recyclerView.setAdapter(madapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Caridata();
                refreshLayout.setRefreshing(false);
            }
        });
        requestdata();
        return view;
    }

    public void Caridata() {
        datalist=new ArrayList<>();
        pg.setMessage("Mengambil Data...");
        pg.setCancelable(false);
        pg.show();
        requestdata();
    }
    public void requestdata(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponeModel> res = api.Popular_Movie(getContext().getResources().getString(R.string.API_KEY));
        res.enqueue(new Callback<ResponeModel>() {
            @Override
            public void onResponse(Call<ResponeModel> call, Response<ResponeModel> response) {
                if (pg != null && pg.isShowing()) {
                    pg.dismiss();
                }
                fr.new Loading(25).execute();
                datalist = response.body().getResults();
                madapter.addData(datalist);
            }

            @Override
            public void onFailure(Call<ResponeModel> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                if (pg != null && pg.isShowing()) {
                    pg.dismiss();
                }
            }
        });
    }

}
