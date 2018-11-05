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
        repositoryMap.put("Kotlin", Arrays.asList(
            new Repository("JetBrains/kotlin", "https://github.com/JetBrains/kotlin", 25068),
            new Repository("shadowsocks/shadowsocks-android", "https://github.com/shadowsocks/shadowsocks-android", 18617),
            new Repository("tipsy/profile-summary-for-github", "https://github.com/tipsy/profile-summary-for-github", 18297)
        ));
        repositoryMap.put("Java", Arrays.asList(
                new Repository("iluwatar/java-design-patterns", "https://github.com/iluwatar/java-design-patterns", 40966),
                new Repository("ReactiveX/RxJava", "https://github.com/ReactiveX/RxJava", 36124),
                new Repository("elastic/elasticsearch", "https://github.com/elastic/elasticsearch", 35811)
        ));
        repositoryMap.put("Scala", Arrays.asList(
                new Repository("apache/spark", "https://github.com/apache/spark", 19371),
                new Repository("apache/predictionio", "https://github.com/apache/predictionio", 11509),
                new Repository("prisma/prisma", "https://github.com/prisma/prisma", 11030)
        ));
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
