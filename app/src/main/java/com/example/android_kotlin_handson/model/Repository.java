package com.example.android_kotlin_handson.model;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("full_name")
    public String fullName;

    @SerializedName("html_url")
    public String htmlUrl;

    @SerializedName("stargazers_count")
    public Integer stargazersCount;

    public Repository(String fullName, String htmlUrl, Integer stargazersCount) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.stargazersCount = stargazersCount;
    }
}
