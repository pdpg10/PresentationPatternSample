package com.example.presentationpatternsample.model.repo

import com.example.presentationpatternsample.model.Post
import com.example.presentationpatternsample.model.net.Api

class PostRepoImpl(private val api: Api) : IPostRepo {

    override suspend fun loadPost(): List<Post> = api.loadPosts()

    companion object {
        private var instance: PostRepoImpl? = null
        fun instance(): IPostRepo {
            var local = instance
            if (local == null) {
                val api = Api.apiInstance()
                local = PostRepoImpl(api)
                instance = local
            }
            return instance!!
        }
    }
}