package com.sun.tino.hottrailers.data.source;

import androidx.lifecycle.LiveData;

import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.sevice.response.GenreResponse;
import com.sun.tino.hottrailers.sevice.response.MovieResponse;

import java.util.List;

import io.reactivex.Single;

public interface MovieDataSource {
    interface Local {
        LiveData<List<Movie>> getAllFavorite();

        LiveData<Movie> getFavoriteById(int idMovie);

        void addFavorite(Movie movie);

        void deleteFavorite(Movie movie);

        boolean isFavorite(int idMovie);
    }

    interface Remote {
        Single<GenreResponse> getGenres();

        Single<MovieResponse> getMoviesTrending();

        Single<MovieResponse> getMoviesByCategory(String categoryType, int page);

        Single<MovieResponse> getMoviesByGenre(String idGenre, int page);

        Single<MovieResponse> getMoviesByCast(String idCast, int page);

        Single<MovieResponse> getMoviesByCompany(int idCompany, int page);

        Single<MovieResponse> searchMovieByName(String key, int page);

        Single<Movie> getMovieDetail(int idMovie);

        Single<Actor> getProfile(String idActor);
    }
}
