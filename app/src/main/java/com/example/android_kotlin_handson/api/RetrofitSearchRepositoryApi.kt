package com.example.android_kotlin_handson.api

import com.example.android_kotlin_handson.api.response.RepositoryEntity
import com.example.android_kotlin_handson.model.Repository
import com.example.android_kotlin_handson.api.response.SearchResult
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitSearchRepositoryApi : SearchRepositoryApi {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service = retrofit.create(GitHubService::class.java)

    interface GitHubService {
        @GET("https://api.github.com/search/repositories?sort=stars&order=desc")
        fun searchRepositoryByLanguage(@Query("q") query: String): Single<SearchResult>
    }

    // Extension function
    private fun SearchResult.toModelList() = items.map { it.toModel() }

    // Extension function
    private fun RepositoryEntity.toModel() = Repository(
            fullName = this.fullName,
            htmlUrl = this.htmlUrl,
            stargazersCount = this.stargazersCount
    )

    override fun searchRepositoryByLanguage(language: String): Single<List<Repository>> {
        val query = "language:$language"
        return service.searchRepositoryByLanguage(query)
                .subscribeOn(Schedulers.newThread())
                .map { it.toModelList() }
    }
}
