package com.sun.tino.hottrailers.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sun.tino.hottrailers.data.model.Movie;

@Database(entities = Movie.class, version = 3, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static final String DB_NAME = "favorite_database";

    private static FavoriteDatabase instance;

    abstract IFavoriteDao favoriteDao();

    static synchronized FavoriteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
