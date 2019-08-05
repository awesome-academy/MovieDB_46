package com.sun.tino.hottrailers.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("id")
    private int mId;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("genres")
    private List<Genre> mGenres;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("production_companies")
    private List<Company> mProductionCompanies;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("runtime")
    private int mRuntime;

    @SerializedName("vote_average")
    private double mVoteAverage;

    @SerializedName("vote_count")
    private int mVoteCount;

    @SerializedName("videos")
    private VideoResult mVideoResult;

    @SerializedName("credits")
    private CastResult mCastResult;

    public Movie() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<Company> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public VideoResult getVideoResult() {
        return mVideoResult;
    }

    public void setVideoResult(VideoResult videoResult) {
        mVideoResult = videoResult;
    }

    public CastResult getCastResult() {
        return mCastResult;
    }

    public void setCastResult(CastResult castResult) {
        mCastResult = castResult;
    }


    public class CastResult {
        @SerializedName("cast")
        private List<Actor> mActors;

        public List<Actor> getActors() {
            return mActors;
        }

        public void setActors(List<Actor> actors) {
            mActors = actors;
        }
    }

    public class VideoResult {
        @SerializedName("results")
        private List<Video> mVideos;

        public List<Video> getVideos() {
            return mVideos;
        }

        public void setVideos(List<Video> videos) {
            mVideos = videos;
        }
    }
}
