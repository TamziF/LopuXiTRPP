package com.example.lopuxi30.data.repositories

import com.example.lopuxi30.data.data_sources.network.AuthRegService
import com.example.lopuxi30.data.models.AuthRegBody
import com.example.lopuxi30.data.models.AuthResponse
import com.example.lopuxi30.data.models.RegResponse
import retrofit2.Response

interface AuthRegRepository {

    suspend fun authorize(authBody: AuthRegBody): Response<AuthResponse>

    suspend fun register(regBody: AuthRegBody): Response<RegResponse>

}

class AuthRegRepositoryImpl(private val authRegApi: AuthRegService): AuthRegRepository {
    override suspend fun authorize(authBody: AuthRegBody): Response<AuthResponse> {
        return authRegApi.authorization(authBody)
    }

    override suspend fun register(regBody: AuthRegBody): Response<RegResponse> {
        return authRegApi.registration(regBody)
    }

}