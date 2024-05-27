package com.example.lopuxi30.ui.screens.create_post

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.lopuxi30.databinding.FragmentCreatePostBinding
import com.example.lopuxi30.ui.recyclers.images.ImagesAdapter
import com.example.lopuxi30.ui.recyclers.images.RecyclerImagesAdapter
import com.example.lopuxi30.ui.stateholders.CreatePostViewModel
import kotlinx.coroutines.launch


class CreatePostFragmentViewController(
    binding: FragmentCreatePostBinding,
    private var fragment: CreatePostFragment?,
    private val viewModel: CreatePostViewModel
) {

    //private val recyclerView = binding.loadedImages
    //private val addImage = binding.addImage
    private val postImage = binding.postImage
    private val descriptionEt = binding.descriptionEt
    private val createPostButton = binding.postButton

    //private val adapter = RecyclerImagesAdapter(arrayListOf(), false)

    fun setupViews() {
        //bindRecycler()
        //bindAddImage()

        bindPostImage()
        bindDescriptionEt()
        bindCreatePostButton()
    }

    private fun bindPostImage() {
        postImage.setOnClickListener {
            openImagePicker()
        }
    }

    /*private fun bindRecycler() {
        recyclerView.layoutManager =
            LinearLayoutManager(fragment!!.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }*/

    /*private fun bindAddImage() {
        addImage.setOnClickListener {
            if (adapter.itemCount < 5) {
                openImagePicker()
            }
        }
    }*/

    private fun bindDescriptionEt() {
        descriptionEt.addTextChangedListener {
            viewModel.setDescription(it.toString())
        }
    }

    private fun bindCreatePostButton() {
        createPostButton.setOnClickListener {
            viewModel.createPost(fragment!!.requireContext())
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private val pickImageLauncher = fragment!!.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val uri = result.data!!.data!!

                postImage.load(uri)
                viewModel.uri = uri
                /*adapter.updateList(uri)
                adapter.notifyDataSetChanged()*/
            }
        }
    }

    fun clear() {
        fragment = null
    }

}