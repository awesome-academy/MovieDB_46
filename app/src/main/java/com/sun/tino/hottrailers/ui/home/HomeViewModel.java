package com.sun.tino.hottrailers.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sun.tino.hottrailers.base.BaseViewModel;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieRepository;
import com.sun.tino.hottrailers.data.source.local.MovieLocalData;
import com.sun.tino.hottrailers.data.source.remote.MovieRemoteData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    private static final int TO_INDEX = 5;
    private static final int FROM_INDEX = 0;

    private MovieRepository mRepository;
    private CompositeDisposable mDisposable;

    private MutableLiveData<List<Movie>> mTopTrendingMovies;

    private ObservableBoolean mIsTopTrendingLoaded = new ObservableBoolean(false);
    public final ObservableBoolean isAllLoaded = new ObservableBoolean(false);

    void initViewModel(Context context) {
        mDisposable = new CompositeDisposable();
        mRepository = MovieRepository.getInstance
                (MovieRemoteData.getInstance(context), MovieLocalData.getInstance(context));
    }

    LiveData<List<Movie>> getTopTrendingMovies() {
        if (mTopTrendingMovies == null) {
            mTopTrendingMovies = new MutableLiveData<>();
            loadTopTrendingMovies();
        }
        return mTopTrendingMovies;
    }

    private void loadTopTrendingMovies() {
        Disposable disposable = mRepository.getMoviesTrending()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    mTopTrendingMovies
                            .setValue(movieResponse.getMovies().subList(FROM_INDEX, TO_INDEX));
                    mIsTopTrendingLoaded.set(true);
                    isAllLoaded.set(isAllLoaded());
                }, throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void handleError(String message) {
    }

    private boolean isAllLoaded() {
        return mIsTopTrendingLoaded.get();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.dispose();
    }
}
