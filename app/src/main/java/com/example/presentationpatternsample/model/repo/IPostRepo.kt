package com.example.presentationpatternsample.model.repo

import com.example.presentationpatternsample.model.Post

interface IPostRepo {
    suspend fun loadPost(): List<Post>
}