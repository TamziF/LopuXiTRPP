package com.example.lopuxi30.ui.recyclers.images

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lopuxi30.databinding.PostImageBinding

class ImageViewHolder(
    private val binding: PostImageBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(imageSource: Uri, isFeed: Boolean) {
        binding.image.load(imageSource)

        if(isFeed) binding.deleteImage.visibility = View.GONE
    }

}