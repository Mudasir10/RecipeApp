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

    public Recipe() {
    }

    public Recipe(String key, String title, String ingredents, String making, String imageUrl) {
        this.key = key;
        this.title = title;
        this.ingredents = ingredents;
        this.making = making;
        this.imageUrl = imageUrl;

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



    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", this.key);
        result.put("title", this.title);
        result.put("ingredents", this.ingredents);
        result.put("making", this.making);
        result.put("imageUrl", this.imageUrl);
        return result;
    }
}
