package com.example.lopuxi30.ui.recyclers.images

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lopuxi30.databinding.PostImageBinding

class RecyclerImagesAdapter(
    val list: ArrayList<Uri>,
    private val isFeed: Boolean
): RecyclerView.Adapter<RecyclerImagesAdapter.ViewHolder>() {

    private lateinit var binding: PostImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PostImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.image.load(list[position])

        if(isFeed) {
            binding.deleteImage.visibility = View.GONE
        }

        binding.deleteImage.setOnClickListener {
            list.remove(list[holder.adapterPosition])
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(binding: PostImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var image: ImageView
        private var deleteImage: ImageView

        init {
            image = binding.image
            deleteImage = binding.deleteImage
        }
    }

    fun updateList(uri: Uri) {
        list.add(uri)
        notifyItemInserted(list.size - 1)
    }

}