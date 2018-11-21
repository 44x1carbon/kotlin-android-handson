package com.example.android_kotlin_handson.model

import com.google.gson.annotations.SerializedName

class Repository(@field:SerializedName("full_name")
                 var fullName: String, @field:SerializedName("html_url")
                 var htmlUrl: String, @field:SerializedName("stargazers_count")
                 var stargazersCount: Int?)
