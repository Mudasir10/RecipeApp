package com.mudasir.recipeapp.models;

import java.util.HashMap;
import java.util.Map;

public class Ratings {
    private String Userid;
    private String Recipeid;

    public Ratings() {
    }

    public Ratings(String userid, String recipeid) {
        Userid = userid;
        Recipeid = recipeid;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getRecipeid() {
        return Recipeid;
    }

    public void setRecipeid(String recipeid) {
        Recipeid = recipeid;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", this.Userid);
        result.put("recipeId", this.Recipeid);
        return result;
    }

}
