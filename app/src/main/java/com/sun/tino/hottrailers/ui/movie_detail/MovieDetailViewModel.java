package com.sun.tino.hottrailers.ui.movie_detail;

import android.content.Context;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import com.sun.tino.hottrailers.base.BaseViewModel;
import com.sun.tino.hottrailers.data.model.Genre;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieRepository;
import com.sun.tino.hottrailers.data.source.local.MovieLocalData;
import com.sun.tino.hottrailers.data.source.remote.MovieRemoteData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends BaseViewModel {
    public final ObservableField<Movie> mMovie = new ObservableField<>();
    public final ObservableList<Genre> mGenres = new ObservableArrayList<>();
    public final ObservableBoolean isShowProgress = new ObservableBoolean(true);
    public final ObservableBoolean isFavorite = new ObservableBoolean(false);
    private CompositeDisposable mDisposables;
    private MovieRepository mRepository;
    private OnTrailerListener mTrailerListener;
    private OnInternetListener mInternetListener;

    public void initViewModel(Context context, OnTrailerListener listener) {
        mTrailerListener = listener;
        mDisposables = new CompositeDisposable();
        mRepository = MovieRepository.getInstance(
                MovieRemoteData.getInstance(context), MovieLocalData.getInstance(context));
    }

    public void setInternetListener(OnInternetListener internetListener) {
        mInternetListener = internetListener;
    }

    public void loadMovieDetail(int movieId) {
        Disposable disposable = mRepository.getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    mMovie.set(movie);
                    mGenres.addAll(movie.getGenres());
                    isShowProgress.set(false);
                    if (!movie.getVideoResult().getVideos().isEmpty()) {
                        mTrailerListener.onCreateTrailer(movie.getVideoResult().getVideos().get(0).getKey());
                    }
                }, throwable -> {
                    if (mInternetListener != null) mInternetListener.onNoInternet();
                });
        mDisposables.add(disposable);

        checkFavorite(movieId);
    }

    public void checkFavorite(int movieId) {
        Disposable disposable = Observable.defer(() -> Observable.just(mRepository.isFavorite(movieId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isFavorite::set);
        mDisposables.add(disposable);
    }

    public void destroy() {
        mDisposables.dispose();
    }
}
