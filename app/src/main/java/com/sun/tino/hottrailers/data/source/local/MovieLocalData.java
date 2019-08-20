package com.sun.tino.hottrailers.data.source.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieDataSource;
import com.sun.tino.hottrailers.data.source.local.background_task.DeleteMovieAsyncTask;
import com.sun.tino.hottrailers.data.source.local.background_task.InsertMovieAsyncTask;

import java.util.List;

public class MovieLocalData implements MovieDataSource.Local {
    private static MovieLocalData sLocalData;
    private IFavoriteDao mDao;
    private LiveData<List<Movie>> mFavorites;

    private MovieLocalData(Context context) {
        FavoriteDatabase database = FavoriteDatabase.getInstance(context);
        mDao = database.favoriteDao();
        mFavorites = mDao.getAllFavorite();
    }

    public static MovieLocalData getInstance(Context context) {
        if (sLocalData == null) sLocalData = new MovieLocalData(context);
        return sLocalData;
    }

    @Override
    public LiveData<List<Movie>> getAllFavorite() {
        return mFavorites;
    }

    @Override
    public LiveData<Movie> getFavoriteById(int idMovie) {
        return mDao.getFavoriteById(idMovie);
    }

    @Override
    public void addFavorite(Movie movie) {
        new InsertMovieAsyncTask(mDao).execute(movie);
    }

    @Override
    public void deleteFavorite(Movie movie) {
        new DeleteMovieAsyncTask(mDao).execute(movie);
    }

    @Override
    public boolean isFavorite(int idMovie) {
        LiveData<Movie> favorite = getFavoriteById(idMovie);
        return favorite != null;
    }
}
