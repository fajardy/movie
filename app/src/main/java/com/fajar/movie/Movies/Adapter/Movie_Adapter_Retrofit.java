package com.fajar.movie.Movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fajar.movie.Movies.Detail_Movie;
import com.fajar.movie.Movies.Model.Result;
import com.fajar.movie.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class Movie_Adapter_Retrofit extends RecyclerView.Adapter<Movie_Adapter_Retrofit.ViewHolder>{
    private Context mcontext;
    private List<Result> list;

    public Movie_Adapter_Retrofit(Context mcontext, List<Result> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_popular,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.rating.setText(String.valueOf(list.get(position).getVoteAverage()));
        Picasso.get().load(list.get(position).getPosterPath()).into(holder.img);

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("MMMM dd, yyyy");
        Date x=new Date();
        try {
            x=input.parse(list.get(position).getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tag.setText(output.format(x));

        final int id = list.get(position).getId();
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, Detail_Movie.class);
                intent.putExtra("id",id);
                mcontext.startActivity(intent);
                customType(mcontext,"fadein-to-fadeout");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, rating, time, tag;
        public ImageView img;
        public CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_cover);
            title = (TextView) itemView.findViewById(R.id.tx_title);
            rating = (TextView) itemView.findViewById(R.id.tx_rating);
            tag = (TextView) itemView.findViewById(R.id.tx_tag);
            card = (CardView) itemView.findViewById(R.id.CardView);
        }
    }

    public void addData(List<Result> list) {
        this.list= list;
        notifyDataSetChanged();
    }
}