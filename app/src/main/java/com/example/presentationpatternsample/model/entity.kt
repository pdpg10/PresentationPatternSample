package com.example.presentationpatternsample.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("id")
    val id: Long,
    val title: String,
    val body: String
)