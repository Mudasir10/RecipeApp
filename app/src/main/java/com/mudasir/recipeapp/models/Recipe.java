package com.mudasir.recipeapp.models;

import android.media.Rating;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

    private String key;
    private String title;
    private String ingredents;
    private String making;
    private String imageUrl;
    private String category;


    public Recipe() {
    }

    public Recipe(String key, String title, String ingredents, String making, String imageUrl,String category) {
        this.key = key;
        this.title = title;
        this.ingredents = ingredents;
        this.making = making;
        this.imageUrl = imageUrl;
        this.category=category;
    }

    public String getIngredents() {
        return ingredents;
    }

    public void setIngredents(String ingredents) {
        this.ingredents = ingredents;
    }

    public String getMaking() {
        return making;
    }

    public void setMaking(String making) {
        this.making = making;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", this.key);
        result.put("title", this.title);
        result.put("ingredents", this.ingredents);
        result.put("making", this.making);
        result.put("imageUrl", this.imageUrl);
        result.put("category",this.category);
        return result;
    }
}
