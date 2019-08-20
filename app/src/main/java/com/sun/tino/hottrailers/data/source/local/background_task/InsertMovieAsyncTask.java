package com.sun.tino.hottrailers.data.source.local.background_task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.local.IFavoriteDao;

public class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Boolean> {

    private IFavoriteDao mDao;

    public InsertMovieAsyncTask(IFavoriteDao dao) {
        mDao = dao;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        return (mDao.insert(movies[0]) != 0);
    }
}
