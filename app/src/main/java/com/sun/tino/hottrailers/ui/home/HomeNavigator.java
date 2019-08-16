package com.sun.tino.hottrailers.ui.home;

import com.sun.tino.hottrailers.data.model.Movie;

public interface HomeNavigator {
    void startMovieDetailActivity(Movie movie);

    void startCategoryActivity(String categoryKey, String categoryTitle);

    void startGenreActivity(String genreKey, String genreTitle);
}
