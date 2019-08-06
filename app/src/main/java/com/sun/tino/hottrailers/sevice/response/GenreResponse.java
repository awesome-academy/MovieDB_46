package com.sun.tino.hottrailers.sevice.response;

import com.google.gson.annotations.SerializedName;
import com.sun.tino.hottrailers.data.model.Genre;

import java.util.List;

public class GenreResponse {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public GenreResponse() {
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }
}
