package com.sun.tino.hottrailers.ui.favorite;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.sun.tino.hottrailers.base.BaseViewModel;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieRepository;
import com.sun.tino.hottrailers.data.source.local.MovieLocalData;
import com.sun.tino.hottrailers.data.source.remote.MovieRemoteData;

import java.util.List;

public class FavoriteViewModel extends BaseViewModel {
    private LiveData<List<Movie>> mMovieFavorite;
    private MovieRepository mRepository;

    public void initViewModel(Context context) {
        mRepository = MovieRepository.getInstance(MovieRemoteData.getInstance(context)
                , MovieLocalData.getInstance(context));
        mMovieFavorite = mRepository.getAllFavorite();
    }

    public LiveData<List<Movie>> getMovies(){
        return mMovieFavorite;
    }

    public void deleteFromFavorite(Movie movie){
        mRepository.deleteFavorite(movie);
    }
}
