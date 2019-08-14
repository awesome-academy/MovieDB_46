package com.sun.tino.hottrailers.data.source;

import androidx.lifecycle.LiveData;

import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.local.MovieLocalData;
import com.sun.tino.hottrailers.data.source.remote.MovieRemoteData;
import com.sun.tino.hottrailers.sevice.response.GenreResponse;
import com.sun.tino.hottrailers.sevice.response.MovieResponse;

import java.util.List;

import io.reactivex.Single;

public class MovieRepository
        implements MovieDataSource.Local, MovieDataSource.Remote {

    private static MovieRepository sMovieRepository;
    private MovieLocalData mLocalData;
    private MovieRemoteData mRemoteData;

    private MovieRepository(MovieRemoteData remoteData, MovieLocalData localData) {
        mLocalData = localData;
        mRemoteData = remoteData;
    }

    public static MovieRepository getInstance(MovieRemoteData remote,
                                              MovieLocalData local) {
        if (sMovieRepository == null) {
            sMovieRepository = new MovieRepository(remote, local);
        }
        return sMovieRepository;
    }

    @Override
    public LiveData<List<Movie>> getAllFavorite() {
        return mLocalData.getAllFavorite();
    }

    @Override
    public LiveData<Movie> getFavoriteById(int idMovie) {
        return mLocalData.getFavoriteById(idMovie);
    }

    @Override
    public void addFavorite(Movie movie) {
        mLocalData.addFavorite(movie);
    }

    @Override
    public void deleteFavorite(Movie movie) {
        mLocalData.deleteFavorite(movie);
    }

    @Override
    public boolean isFavorite(int idMovie) {
        return mLocalData.isFavorite(idMovie);
    }

    @Override
    public Single<GenreResponse> getGenres() {
        return mRemoteData.getGenres();
    }

    @Override
    public Single<MovieResponse> getMoviesTrending() {
        return mRemoteData.getMoviesTrending();
    }

    @Override
    public Single<MovieResponse> getMoviesByCategory(String categoryType, int page) {
        return mRemoteData.getMoviesByCategory(categoryType, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByGenre(String idGenre, int page) {
        return mRemoteData.getMoviesByGenre(idGenre, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByCast(String idCast, int page) {
        return mRemoteData.getMoviesByCast(idCast, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByCompany(int idCompany, int page) {
        return mRemoteData.getMoviesByCompany(idCompany, page);
    }

    @Override
    public Single<MovieResponse> searchMovieByName(String key, int page) {
        return mRemoteData.searchMovieByName(key, page);
    }

    @Override
    public Single<Movie> getMovieDetail(int idMovie) {
        return mRemoteData.getMovieDetail(idMovie);
    }

    @Override
    public Single<Actor> getProfile(String idActor) {
        return mRemoteData.getProfile(idActor);
    }
}
