package com.example.lopuxi30.ui.recyclers.feed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lopuxi30.R
import com.example.lopuxi30.data.data_sources.network.BASE_URL
import com.example.lopuxi30.data.models.Post
import com.example.lopuxi30.databinding.FeedItemBinding
import java.text.SimpleDateFormat

class PostViewHolder(private val binding: FeedItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, token: String) {
        binding.usernameTv.text = post.author
        binding.timeTv.text = SimpleDateFormat("HH:mm dd MMM").format(post.time)
        binding.text.text = post.text

        if(post.photos.isNotEmpty()) {
            binding.postItemImage.visibility = View.VISIBLE
            binding.postItemImage.load("${BASE_URL}file/${post.photos[0]}") {
                addHeader("Authorization", token)
                error(R.drawable.baseline_image)
                placeholder(R.drawable.loading_animation)
            }
        } else {
            binding.postItemImage.visibility = View.GONE
        }
    }

}