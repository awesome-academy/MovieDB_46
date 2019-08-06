package com.sun.tino.hottrailers.sevice;

import com.sun.tino.hottrailers.data.model.Actor;
import com.sun.tino.hottrailers.data.model.Movie;
import com.sun.tino.hottrailers.sevice.response.GenreResponse;
import com.sun.tino.hottrailers.sevice.response.MovieResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApi {
    @GET("genre/movie/list")
    Single<GenreResponse> getGenres();

    @GET("trending/movie/day")
    Observable<MovieResponse> getMoviesTrending();

    @GET("movie/{type}")
    Observable<MovieResponse> getMoviesByCategory(@Path("type") String type,
                                                  @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByGenre(@Query("with_genres") String idGenre,
                                               @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByCast(@Query("with_cast") String idCast,
                                              @Query("page") int page);

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByCompany(@Query("with_companies") int idCompany,
                                                 @Query("page") int page);
    @GET("search/movie")
    Observable<MovieResponse> searchMovieByName(@Query("query") String key,
                                                @Query("page") int page);

    @GET("movie/{movie_id}?append_to_response=credits,videos")
    Single<Movie> getMovieDetail(@Path("movie_id") int id);

    @GET("person/{actor_id}")
    Single<Actor> getProfile(@Path("actor_id") String actorId);
}
