package com.example.android_kotlin_handson.api

import com.example.android_kotlin_handson.model.Repository

import io.reactivex.Single

interface SearchRepositoryApi {
    fun searchRepositoryByLanguage(language: String): Single<List<Repository>>
}
