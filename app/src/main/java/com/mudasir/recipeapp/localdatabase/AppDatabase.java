package com.mudasir.recipeapp.localdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mudasir.recipeapp.dao.FavoriteDAO;
import com.mudasir.recipeapp.models.RecipeModelForRoom;

@Database(entities={RecipeModelForRoom.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mInstance;
    private static final String DATABASE_NAME = "db";
    public abstract FavoriteDAO favDao();
    public synchronized static AppDatabase getDatabaseInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
    public static void destroyInstance() {
        mInstance = null;
    }
}
