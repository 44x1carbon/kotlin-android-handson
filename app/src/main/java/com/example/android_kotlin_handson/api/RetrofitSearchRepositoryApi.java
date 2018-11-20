package com.example.android_kotlin_handson.api;

import com.example.android_kotlin_handson.model.Repository;
import com.example.android_kotlin_handson.model.SearchResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitSearchRepositoryApi implements SearchRepositoryApi {
    public interface GitHubService {
        @GET("https://api.github.com/search/repositories?sort=stars&order=desc")
        Single<SearchResult> searchRepositoryByLanguage(@Query("q") String query);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GitHubService service = retrofit.create(GitHubService.class);


    @Override
    public Single<List<Repository>> searchRepositoryByLanguage(String language) {
        String query = "language:" + language;
        return service.searchRepositoryByLanguage(query)
                .subscribeOn(Schedulers.newThread())
                .map(searchResult -> searchResult.items);
    }
}
