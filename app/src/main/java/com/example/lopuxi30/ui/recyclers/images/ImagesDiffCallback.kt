package com.example.lopuxi30.ui.recyclers.images

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

class ImagesDiffCallback: DiffUtil.ItemCallback<Uri>() {
    override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean {
        return oldItem == newItem
    }
}