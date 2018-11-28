package com.example.android_kotlin_handson.model;

public class Repository {
    public String fullName;

    public String htmlUrl;

    public Integer stargazersCount;

    public Repository(String fullName, String htmlUrl, Integer stargazersCount) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.stargazersCount = stargazersCount;
    }
}
