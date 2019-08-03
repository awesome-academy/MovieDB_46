package com.sun.tino.hottrailers.data.model;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    private String mId;
    @SerializedName("iso_639_1")
    private String mIso639;
    @SerializedName("iso_3166_1")
    private String mIso3166;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private int mSize;
    @SerializedName("type")
    private String mType;

    public Video() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIso639() {
        return mIso639;
    }

    public void setIso639(String iso639) {
        mIso639 = iso639;
    }

    public String getIso3166() {
        return mIso3166;
    }

    public void setIso3166(String iso3166) {
        mIso3166 = iso3166;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
