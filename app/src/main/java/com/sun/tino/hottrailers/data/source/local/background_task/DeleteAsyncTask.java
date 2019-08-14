package com.sun.tino.hottrailers.data.source.local.background_task;

import android.os.AsyncTask;

import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.local.IFavoriteDao;

public class DeleteAsyncTask extends AsyncTask<Movie, Void, Void> {

    private IFavoriteDao mDao;

    public DeleteAsyncTask(IFavoriteDao dao) {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        mDao.delete(movies[0]);
        return null;
    }
}
