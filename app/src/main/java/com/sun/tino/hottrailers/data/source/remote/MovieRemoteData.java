package com.sun.tino.hottrailers.data.source.remote;

import android.content.Context;

import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.data.source.MovieDataSource;
import com.sun.tino.hottrailers.sevice.NetworkService;
import com.sun.tino.hottrailers.sevice.RequestApi;
import com.sun.tino.hottrailers.sevice.response.GenreResponse;
import com.sun.tino.hottrailers.sevice.response.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MovieRemoteData implements MovieDataSource.Remote {
    private static MovieRemoteData sRemoteData;
    private RequestApi mApi;

    private MovieRemoteData(RequestApi api) {
        mApi = api;
    }

    public static MovieRemoteData getInstance(Context context) {
        if (sRemoteData == null) {
            sRemoteData = new MovieRemoteData(NetworkService.getInstance(context));
        }
        return sRemoteData;
    }

    @Override
    public Single<GenreResponse> getGenres() {
        return mApi.getGenres();
    }

    @Override
    public Single<MovieResponse> getMoviesTrending() {
        return mApi.getMoviesTrending();
    }

    @Override
    public Single<MovieResponse> getMoviesByCategory(String categoryType, int page) {
        return mApi.getMoviesByCategory(categoryType, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByGenre(String idGenre, int page) {
        return mApi.getMoviesByGenre(idGenre, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByCast(String idCast, int page) {
        return mApi.getMoviesByCast(idCast, page);
    }

    @Override
    public Single<MovieResponse> getMoviesByCompany(int idCompany, int page) {
        return mApi.getMoviesByCompany(idCompany, page);
    }

    @Override
    public Single<MovieResponse> searchMovieByName(String key, int page) {
        return mApi.searchMovieByName(key, page);
    }

    @Override
    public Single<Movie> getMovieDetail(int idMovie) {
        return mApi.getMovieDetail(idMovie);
    }

    @Override
    public Single<Actor> getProfile(String idActor) {
        return null;
    }
}
