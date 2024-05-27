package com.example.lopuxi30.ui.recyclers.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lopuxi30.data.models.Post
import com.example.lopuxi30.databinding.FeedItemBinding

class FeedAdapter(
    private val token: String
): ListAdapter<Post, PostViewHolder>(
    PostDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), token)
    }
}