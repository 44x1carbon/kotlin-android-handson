package com.example.android_kotlin_handson.api;

import com.example.android_kotlin_handson.model.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HashMapSearchRepositoryApi implements SearchRepositoryApi {
    private HashMap<String, List<Repository>> repositoryMap;

    public HashMapSearchRepositoryApi() {
        this.repositoryMap = new HashMap<>();
    }

    @Override
    public Single<List<Repository>> searchRepositoryByLanguage(final String language) {
        return Single.create(subscriber -> {
            List<Repository> repositoryList = repositoryMap.get(language);
            if(repositoryList == null) {
                repositoryList = new ArrayList<>();
            }
            subscriber.onSuccess(repositoryList);
        });
    }
}
