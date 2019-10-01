package com.example.presentationpatternsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.presentationpatternsample.R
import com.example.presentationpatternsample.presentation.IMainActivityViewModel
import com.example.presentationpatternsample.presentation.MainActivityViewModelImpl
import com.example.presentationpatternsample.presentation.State
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { PostAdapter(this) }
    private var dialog: AlertDialog? = null
    private var viewModel: IMainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.adapter = adapter
        viewModel = MainActivityViewModelImpl()
    }

    override fun onResume() {
        super.onResume()
        observeData()
    }

    private fun observeData() {

        viewModel?.states()
            ?.observe(this,
                Observer { onResultState(it) })

        viewModel?.loadPosts()
            ?.observe(this,
                Observer { adapter.addItems(it) })

    }

    private fun onResultState(it: State?) {
        Log.d("onResultState","loadPosts $it")
        if (it == null) return
        when (it) {
            State.DATA_LOADING_STOP -> hideProgress()
            State.DATA_LOADING_START -> showProgress()
            State.FAIL -> onFail()
        }
    }

    private fun showProgress() {
        dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_progress)
            .setCancelable(false)
            .create()
        dialog?.show()
    }

    private fun hideProgress() {
        dialog?.hide()
    }

    private fun onFail() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Fail")
            .setMessage("Something went wrong please try again")
            .setPositiveButton("ok", null)
            .create()
        alertDialog.show()
    }

    override fun onStop() {
        super.onStop()
//        presenter?.cancel()
    }
}

