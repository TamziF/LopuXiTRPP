package com.example.lopuxi30.di

import com.example.lopuxi30.data.data_sources.network.AuthRegService
import com.example.lopuxi30.data.data_sources.network.CreatePostService
import com.example.lopuxi30.data.data_sources.network.FeedService
import com.example.lopuxi30.data.data_sources.network.Network
import com.example.lopuxi30.data.repositories.AuthRegRepository
import com.example.lopuxi30.data.repositories.AuthRegRepositoryImpl
import com.example.lopuxi30.data.repositories.CreatePostRepository
import com.example.lopuxi30.data.repositories.CreatePostRepositoryImpl
import com.example.lopuxi30.data.repositories.FeedRepository
import com.example.lopuxi30.data.repositories.FeedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetwork(): Network {
        return Network()
    }

    @Provides
    @Singleton
    fun provideAuthRegApi(network: Network): AuthRegService {
        return network.authRegApi
    }

    @Provides
    @Singleton
    fun provideCreatePostApi(network: Network): CreatePostService {
        return network.createPostApi
    }

    @Provides
    @Singleton
    fun provideFeedApi(network: Network): FeedService {
        return network.feedApi
    }

    @Provides
    @Singleton
    fun provideAuthRegRepository(authRegApi: AuthRegService): AuthRegRepository {
        return AuthRegRepositoryImpl(authRegApi)
    }

    @Provides
    @Singleton
    fun provideCreatePostRepository(createPostApi: CreatePostService): CreatePostRepository {
        return CreatePostRepositoryImpl(createPostApi)
    }

    @Provides
    @Singleton
    fun provideFeedRepository(feedApi: FeedService): FeedRepository {
        return FeedRepositoryImpl(feedApi)
    }
}