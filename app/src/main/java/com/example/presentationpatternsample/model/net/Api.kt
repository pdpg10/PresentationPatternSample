package com.example.presentationpatternsample.model.net

import com.example.presentationpatternsample.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface Api {

    @GET("posts")
    suspend fun loadPosts(): List<Post>

    companion object {
        private var instance: Api? = null
        fun apiInstance(): Api {
            var localApi = instance
            if (localApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                localApi = retrofit.create(Api::class.java)
                instance = localApi
            }
            return instance!!
        }
    }
}

