package com.mudasir.recipeapp.models;

import java.util.HashMap;
import java.util.Map;

public class Comments {

    private String userId;
    private String UserName;
    private String message;

    public Comments() {
    }

    public Comments(String userId, String userName, String message) {
        this.userId = userId;
        UserName = userName;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", this.userId);
        result.put("userName", this.userId);
        result.put("comment", this.message);
        return result;
    }
}
