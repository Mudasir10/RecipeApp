package com.mudasir.recipeapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mudasir.recipeapp.models.RecipeModelForRoom;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    public void addData(RecipeModelForRoom favoriteList);

    @Query("select * from favoritelist")
    public List<RecipeModelForRoom> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE `key`=:id)")
    public int isFavorite(String id);

    @Delete
    public void delete(RecipeModelForRoom favoriteList);

}
