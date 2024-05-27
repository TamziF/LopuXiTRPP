package com.example.lopuxi30.data.data_sources.network

import com.example.lopuxi30.data.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface FeedService {
    @GET
    suspend fun getFeed(
        @Header("Authorization") token: String,
        @Url url: String
    ): Response<ArrayList<Post>>
}