package com.example.presentationpatternsample.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentationpatternsample.model.Post
import com.example.presentationpatternsample.model.repo.IPostRepo
import com.example.presentationpatternsample.model.repo.PostRepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivityViewModelImpl : IMainActivityViewModel, CoroutineScope {

    private val repo: IPostRepo = PostRepoImpl.instance()

    private val mainJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = mainJob

    private val livePosts = MutableLiveData<List<Post>>()
    private val liveState = MutableLiveData<State>()


    override fun loadPosts(): LiveData<List<Post>> {
        liveState.postValue(State.DATA_LOADING_START)
        launch(Dispatchers.IO) {
            try {
                val items = repo.loadPost()
                livePosts.postValue(items)
                Log.d("loadPosts", "loadPosts ok")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("loadPosts", "loadPosts fail")
                liveState.postValue(State.FAIL)
            } finally {
                Log.d("loadPosts", "loadPosts finnaly")
                liveState.postValue(State.DATA_LOADING_STOP)
            }
        }
        return livePosts
    }

    override fun states(): LiveData<State> = liveState

//    override fun cancel() {
//        mainJob.cancel()
//    }

}