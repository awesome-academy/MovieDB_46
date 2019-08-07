package com.sun.tino.hottrailers.sevice.response;

import com.google.gson.annotations.SerializedName;
import com.sun.tino.hottrailers.data.model.Movie;

import java.util.List;

public class MovieResponse {

    @SerializedName("total_results")
    private int mTotalResult;

    @SerializedName("results")
    private List<Movie> mMovies;

    public MovieResponse() {
    }

    public int getTotalResult() {
        return mTotalResult;
    }

    public void setTotalResult(int totalResult) {
        mTotalResult = totalResult;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
