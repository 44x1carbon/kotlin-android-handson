package com.example.android_kotlin_handson.api

import com.example.android_kotlin_handson.model.Repository

import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class HashMapSearchRepositoryApi : SearchRepositoryApi {
    private val repositoryMap: HashMap<String, List<Repository>>

    init {
        this.repositoryMap = HashMap()
        repositoryMap["Kotlin"] = Arrays.asList(
                Repository("JetBrains/kotlin", "https://github.com/JetBrains/kotlin", 25068),
                Repository("shadowsocks/shadowsocks-android", "https://github.com/shadowsocks/shadowsocks-android", 18617),
                Repository("tipsy/profile-summary-for-github", "https://github.com/tipsy/profile-summary-for-github", 18297)
        )
        repositoryMap["Java"] = Arrays.asList(
                Repository("iluwatar/java-design-patterns", "https://github.com/iluwatar/java-design-patterns", 40966),
                Repository("ReactiveX/RxJava", "https://github.com/ReactiveX/RxJava", 36124),
                Repository("elastic/elasticsearch", "https://github.com/elastic/elasticsearch", 35811)
        )
        repositoryMap["Scala"] = Arrays.asList(
                Repository("apache/spark", "https://github.com/apache/spark", 19371),
                Repository("apache/predictionio", "https://github.com/apache/predictionio", 11509),
                Repository("prisma/prisma", "https://github.com/prisma/prisma", 11030)
        )
    }

    override fun searchRepositoryByLanguage(language: String): Single<List<Repository>> {
        return Single.create { subscriber ->
            var repositoryList: List<Repository>? = repositoryMap[language]
            if (repositoryList == null) {
                repositoryList = ArrayList()
            }
            subscriber.onSuccess(repositoryList)
        }
    }
}
