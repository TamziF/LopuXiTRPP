package com.example.lopuxi30.ui.recyclers.feed

import androidx.recyclerview.widget.DiffUtil
import com.example.lopuxi30.data.models.Post

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return (oldItem.author == newItem.author && oldItem.text == newItem.text && oldItem.time == newItem.time)
    }
}