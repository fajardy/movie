package com.fajar.movie.Movies.Model.TimeGenre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimedanGenre {
    @SerializedName("runtime")
    @Expose
    public int runtime;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres;

    public TimedanGenre(int runtime, List<Genre> genres) {
        this.runtime = runtime;
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
