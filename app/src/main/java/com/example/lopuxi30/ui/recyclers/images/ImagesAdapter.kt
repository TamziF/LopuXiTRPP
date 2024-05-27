package com.example.lopuxi30.ui.recyclers.images

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lopuxi30.databinding.PostImageBinding

class ImagesAdapter(
    private val isFeed: Boolean
) : ListAdapter<Uri, ImageViewHolder>(ImagesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = PostImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position), isFeed)
    }
}