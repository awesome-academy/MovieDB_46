package com.sun.tino.hottrailers.data.model;

import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public Genre() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
