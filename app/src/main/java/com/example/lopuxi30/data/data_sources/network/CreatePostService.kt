package com.example.lopuxi30.data.data_sources.network

import com.example.lopuxi30.data.models.CreatePostBody
import com.example.lopuxi30.data.models.CreatePostResponse
import com.example.lopuxi30.data.models.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface CreatePostService {

    @Multipart
    @Headers(
        "Connect-TimeOut: 240", "Read-TimeOut: 240"
    )
    @POST("upload")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<UploadImageResponse>

    @POST("post")
    suspend fun createPost(
        @Header("Authorization") token: String,
        @Body post: CreatePostBody
    ): Response<CreatePostResponse>

}