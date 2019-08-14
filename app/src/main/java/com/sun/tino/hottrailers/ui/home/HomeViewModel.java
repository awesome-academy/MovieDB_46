package com.sun.tino.hottrailers.ui.home;

import android.content.Context;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.sun.tino.hottrailers.base.BaseViewModel;
import com.sun.tino.hottrailers.data.model.CategoryKey;
import com.sun.tino.hottrailers.data.model.CategoryName;
import com.sun.tino.hottrailers.data.model.Genre;
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
    private static final int FIRST_PAGE = 1;
    private static final int TO_INDEX = 5;
    private static final int FROM_INDEX = 0;

    private MovieRepository mRepository;
    private CompositeDisposable mDisposable;

    private MutableLiveData<List<Genre>> mGenres;
    private MutableLiveData<List<Movie>> mTopTrendingMovies;
    public final ObservableList<ObservableList<Movie>> categoryMovies = new ObservableArrayList<>();
    public final ObservableList<String> categoryTitle = new ObservableArrayList<>();

    private ObservableList<Movie> nowPlayingMovies = new ObservableArrayList<>();
    private ObservableList<Movie> upComingMovies = new ObservableArrayList<>();
    private ObservableList<Movie> popularMovies = new ObservableArrayList<>();
    private ObservableList<Movie> topRateMovies = new ObservableArrayList<>();
    private ObservableBoolean mIsTopTrendingLoaded = new ObservableBoolean(false);
    private ObservableBoolean mIsUpComingLoaded = new ObservableBoolean(false);
    private ObservableBoolean mIsNowPlayingLoaded = new ObservableBoolean(false);
    private ObservableBoolean mIsTopRatedLoaded = new ObservableBoolean(false);
    private ObservableBoolean mIsPopularLoaded = new ObservableBoolean(false);
    private ObservableBoolean mIsGenresLoaded = new ObservableBoolean(false);

    public final ObservableBoolean isAllLoaded = new ObservableBoolean(false);

    void initViewModel(Context context) {
        mDisposable = new CompositeDisposable();
        mRepository = MovieRepository.getInstance
                (MovieRemoteData.getInstance(context), MovieLocalData.getInstance(context));
        loadCategories();
    }

    LiveData<List<Movie>> getTopTrendingMovies() {
        if (mTopTrendingMovies == null) {
            mTopTrendingMovies = new MutableLiveData<>();
            loadTopTrendingMovies();
        }
        return mTopTrendingMovies;
    }

    LiveData<List<Genre>> getGenres() {
        if (mGenres == null) {
            mGenres = new MutableLiveData<>();
            loadGenres();
        }
        return mGenres;
    }

    private void loadCategories() {
        loadTopRateMovies();
        loadPopularMovies();
        loadUpComingMovies();
        loadNowPlayingMovies();
    }

    private void loadNowPlayingMovies() {
        Disposable disposable = mRepository.getMoviesByCategory(CategoryKey.CATEGORY_NOW_PLAYING, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    nowPlayingMovies.addAll(movieResponse.getMovies());
                    categoryMovies.add(nowPlayingMovies);
                    categoryTitle.add(CategoryName.TITLE_NOW_PLAYING);
                    mIsNowPlayingLoaded.set(true);
                    isAllLoaded.set(isAllLoaded());
                }, throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void loadUpComingMovies() {
        Disposable disposable = mRepository.getMoviesByCategory(CategoryKey.CATEGORY_UP_COMING, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    upComingMovies.addAll(movieResponse.getMovies());
                    categoryMovies.add(upComingMovies);
                    categoryTitle.add(CategoryName.TITLE_UP_COMING);
                    mIsUpComingLoaded.set(true);
                    isAllLoaded.set(isAllLoaded());
                }, throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void loadTopRateMovies() {
        Disposable disposable = mRepository.getMoviesByCategory(CategoryKey.CATEGORY_TOP_RATE, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    topRateMovies.addAll(movieResponse.getMovies());
                    categoryMovies.add(topRateMovies);
                    categoryTitle.add(CategoryName.TITLE_TOP_RATE);
                    mIsTopRatedLoaded.set(true);
                    isAllLoaded.set(isAllLoaded());
                }, throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void loadPopularMovies() {
        Disposable disposable = mRepository.getMoviesByCategory(CategoryKey.CATEGORY_POPULAR, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    popularMovies.addAll(movieResponse.getMovies());
                    categoryMovies.add(popularMovies);
                    categoryTitle.add(CategoryName.TITLE_POPULAR);
                    mIsPopularLoaded.set(true);
                    isAllLoaded.set(isAllLoaded());
                }, throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void loadGenres() {
        Disposable disposable = mRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genreResponse -> {
                            mGenres.setValue(genreResponse.getGenres());
                            mIsGenresLoaded.set(true);
                            isAllLoaded.set(isAllLoaded());
                        },
                        throwable -> handleError(throwable.getMessage()));
        mDisposable.add(disposable);
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

    private boolean isAllLoaded() {
        return mIsTopTrendingLoaded.get()
                && mIsGenresLoaded.get()
                && mIsPopularLoaded.get()
                && mIsTopRatedLoaded.get()
                && mIsUpComingLoaded.get()
                && mIsNowPlayingLoaded.get();
    }

    void dispose() {
        mDisposable.dispose();
    }

    private void handleError(String message) {
    }
}
