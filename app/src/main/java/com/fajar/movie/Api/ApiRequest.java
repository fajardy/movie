package com.fajar.movie.Api;

import com.fajar.movie.Movies.Model.ResponeModel;
import com.fajar.movie.Movies.Model.TimeGenre.TimedanGenre;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("movie/popular")
    Call<ResponeModel> Popular_Movie(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<ResponeModel> Top_Rated_Movie(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<ResponeModel> Upcoming_Movie(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<ResponeModel> Now_Playing_Movie(@Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<TimedanGenre> timeandgenre(@Path("movie_id") int id, @Query("api_key") String api_key);
}
