package com.example.presentationpatternsample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentationpatternsample.R
import com.example.presentationpatternsample.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(context: Context) : RecyclerView.Adapter<PostAdapter.PostVH>() {
    private val posts = mutableListOf<Post>()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val v = inflater.inflate(R.layout.item_post, parent, false)
        return PostVH(v)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        val it = posts[position]
        holder.onBind(it)
    }

    fun addItems(it: List<Post>) {
        posts.clear()
        posts.addAll(it)
        notifyItemRangeInserted(0, it.size)
    }

    class PostVH(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitle: TextView
        val tvBody: TextView

        init {
            tvBody = itemView.tv_body
            tvTitle = itemView.tv_title
        }

        fun onBind(it: Post) {
            tvTitle.text = it.title
            tvBody.text = it.body
        }
    }
}