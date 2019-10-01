package com.example.presentationpatternsample.presentation

import androidx.lifecycle.LiveData
import com.example.presentationpatternsample.model.Post

interface IMainActivityViewModel {

    fun loadPosts(): LiveData<List<Post>>

    fun states(): LiveData<State>
}