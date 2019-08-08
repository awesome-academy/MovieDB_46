package com.sun.tino.hottrailers.ui.home;

import androidx.lifecycle.ViewModel;

import com.sun.tino.hottrailers.data.model.Movie;

public class ItemMovieViewModel extends ViewModel {
    private Movie mMovie;

    public ItemMovieViewModel() {
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }

    public String getReleaseDate() {
        return mMovie.getReleaseDate();
    }

    public double getVoteAverage() {
        return mMovie.getVoteAverage();
    }
}
