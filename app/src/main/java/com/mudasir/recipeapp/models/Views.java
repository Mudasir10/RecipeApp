package com.mudasir.recipeapp.models;

import java.util.HashMap;
import java.util.Map;

public class Views {
    private String userId;
    private Boolean status;


    public Views() {
    }

    public Views(String userId, Boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", this.userId);
        result.put("status", this.status);
        return result;
    }

}
