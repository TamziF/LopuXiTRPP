package com.example.lopuxi30.data.data_sources.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://mouse-enormous-probably.ngrok-free.app/"

class Network {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofitObject: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val authRegApi: AuthRegService = retrofitObject.create(AuthRegService::class.java)
    val createPostApi: CreatePostService = retrofitObject.create(CreatePostService::class.java)
    val feedApi: FeedService = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(FeedService::class.java)
}