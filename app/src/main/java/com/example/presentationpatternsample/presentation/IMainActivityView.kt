package com.example.presentationpatternsample.presentation

import com.example.presentationpatternsample.model.Post

interface IMainActivityView {
    fun showProgress()

    fun hideProgress()

    fun addItems(posts: List<Post>)

    fun onFail()
}