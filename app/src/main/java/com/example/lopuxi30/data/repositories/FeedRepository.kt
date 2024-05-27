package com.example.lopuxi30.data.repositories

import com.example.lopuxi30.data.data_sources.network.FeedService
import com.example.lopuxi30.data.models.Post
import retrofit2.Response

interface FeedRepository {

    suspend fun getFeed(token: String, url: String): Response<ArrayList<Post>>

}

class FeedRepositoryImpl(
    private val feedApi: FeedService
): FeedRepository {
    override suspend fun getFeed(token: String, url: String): Response<ArrayList<Post>> {
        return feedApi.getFeed(token, url)
    }

}