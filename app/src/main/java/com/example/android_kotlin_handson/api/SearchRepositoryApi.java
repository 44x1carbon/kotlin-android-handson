package com.example.android_kotlin_handson.api;

import com.example.android_kotlin_handson.model.Repository;

import java.util.List;

import io.reactivex.Single;

public interface SearchRepositoryApi {
    Single<List<Repository>> searchRepositoryByLanguage(String language);
}
