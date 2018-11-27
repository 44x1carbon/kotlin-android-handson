package com.example.android_kotlin_handson.api.response;

import com.google.gson.annotations.SerializedName;

public class RepositoryEntity {
    @SerializedName("full_name")
    public String fullName;

    @SerializedName("html_url")
    public String htmlUrl;

    @SerializedName("stargazers_count")
    public Integer stargazersCount;

    public RepositoryEntity(String fullName, String htmlUrl, Integer stargazersCount) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.stargazersCount = stargazersCount;
    }
}
