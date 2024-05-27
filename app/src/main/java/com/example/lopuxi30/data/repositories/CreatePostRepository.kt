package com.example.lopuxi30.data.repositories

import com.example.lopuxi30.data.data_sources.network.CreatePostService
import com.example.lopuxi30.data.models.CreatePostBody
import com.example.lopuxi30.data.models.CreatePostResponse
import com.example.lopuxi30.data.models.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface CreatePostRepository {

    suspend fun uploadImage(token: String, file: MultipartBody.Part): Response<UploadImageResponse>

    suspend fun createPost(token: String, post: CreatePostBody): Response<CreatePostResponse>

}

class CreatePostRepositoryImpl(
    private val createPostApi: CreatePostService
): CreatePostRepository {
    override suspend fun uploadImage(
        token: String,
        file: MultipartBody.Part
    ): Response<UploadImageResponse> {
        return createPostApi.uploadImage(token, file)
    }

    override suspend fun createPost(
        token: String,
        post: CreatePostBody
    ): Response<CreatePostResponse> {
        return createPostApi.createPost(token, post)
    }

}