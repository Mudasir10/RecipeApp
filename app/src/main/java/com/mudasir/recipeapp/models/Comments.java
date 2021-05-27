package com.mudasir.recipeapp.models;

import java.util.HashMap;
import java.util.Map;

public class Comments {

    private String userId;
    private String userName;
    private String comment;

    public Comments() {
    }

    public Comments(String userId, String userName, String message) {
        this.userId = userId;
        this.userName = userName;
        this.comment = message;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", this.userId);
        result.put("userName", this.userName);
        result.put("comment", this.comment);
        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
