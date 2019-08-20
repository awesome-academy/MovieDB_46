package com.sun.tino.hottrailers.ui.category;

import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieRepository;
import com.sun.tino.hottrailers.data.source.local.MovieLocalData;
import com.sun.tino.hottrailers.data.source.remote.MovieRemoteData;
import com.sun.tino.hottrailers.ui.movie_detail.OnInternetListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieHorizontalViewModel {
    private static final int DEFAULT_PAGE = 1;
    public final ObservableList<Movie> movieHorizontal = new ObservableArrayList<>();
    private String mCategoryKey;
    private int mPage;
    private MovieRepository mMovieRepository;
    private CompositeDisposable mCompositeDisposable;
    private MovieHorizontalListener mListener;
    private OnInternetListener mOnInternetListener;

    public MovieHorizontalViewModel(Context context, MovieHorizontalListener listener) {
        mPage = DEFAULT_PAGE;
        mMovieRepository = MovieRepository.getInstance
                (MovieRemoteData.getInstance(context), MovieLocalData.getInstance(context));
        mListener = listener;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setOnInternetListener(OnInternetListener onInternetListener) {
        mOnInternetListener = onInternetListener;
    }

    public void setCategoryKey(String categoryKey) {
        mCategoryKey = categoryKey;
    }

    public int getPage() {
        return mPage;
    }

    public void loadMoviesCategory(int page) {
        mPage = page;
        Disposable disposable = mMovieRepository.getMoviesByCategory(mCategoryKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    movieHorizontal.clear();
                    movieHorizontal.addAll(movieResponse.getMovies());
                    mListener.OnHideLoadAllData(true);
                    mListener.OnHideLoadMore(true);
                }, throwable -> {
                    mOnInternetListener.onNoInternet();
                });
        mCompositeDisposable.add(disposable);
    }

    public void despose() {
        mCompositeDisposable.dispose();
    }

    public interface MovieHorizontalListener {
        void OnHideLoadAllData(boolean isHide);

        void OnHideLoadMore(boolean isHide);
    }
}
