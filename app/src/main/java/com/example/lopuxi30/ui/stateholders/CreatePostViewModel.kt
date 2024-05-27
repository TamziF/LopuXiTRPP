package com.example.lopuxi30.ui.stateholders

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lopuxi30.data.models.CreatePostBody
import com.example.lopuxi30.data.repositories.CreatePostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject


@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostRepository: CreatePostRepository
) : ViewModel() {

    private val postBody = CreatePostBody("", "", arrayListOf(""))

    var token = ""

    var uri: Uri = "".toUri()

    fun setUsername(username: String) {
        postBody.author = username
    }

    fun setDescription(description: String) {
        postBody.text = description
    }

    fun createPost(context: Context?) {
        viewModelScope.launch(Dispatchers.IO) {
            loadImage(uri, context)
            //createPostRepository.createPost(token, postBody)
        }
    }

    private fun loadImage(uri: Uri, context: Context?) {
        val path = getRealPathFromUri(uri, context)

        val file = File(path)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        //val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        viewModelScope.launch(Dispatchers.IO) {
            val response = createPostRepository.uploadImage(token, body)
            if (response.isSuccessful && response.body() != null) {
                postBody.photosID[0] = (response.body()!!.uri)
                createPostRepository.createPost(token, postBody)
            }
        }
    }

    private fun getRealPathFromUri(uri: Uri?, context: Context?): String {
        var filePath = ""
        if (context != null && uri != null) {
            var cursor: Cursor? = null
            try {
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                cursor = context.contentResolver.query(uri, projection, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    filePath = cursor.getString(columnIndex)
                }
            } finally {
                cursor?.close()
            }
        }
        return filePath
    }

}