package com.example.android_kotlin_handson.api.response

import com.google.gson.annotations.SerializedName

data class RepositoryEntity(
        @field:SerializedName("full_name") val fullName: String,
        @field:SerializedName("html_url") val htmlUrl: String,
        @field:SerializedName("stargazers_count") val stargazersCount: Int
)