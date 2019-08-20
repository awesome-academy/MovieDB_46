package com.sun.tino.hottrailers.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sun.tino.hottrailers.data.model.Movie;

import java.util.List;

@Dao
public interface IFavoriteDao {
    @Insert
    long insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Movie>> getAllFavorite();

    @Query("SELECT * FROM favorite_table WHERE id = :idMovie LIMIT 1")
    LiveData<Movie> getFavoriteById(int idMovie);
}
