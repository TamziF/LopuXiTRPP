package com.example.lopuxi30.data.data_sources.network

import com.example.lopuxi30.data.models.AuthRegBody
import com.example.lopuxi30.data.models.AuthResponse
import com.example.lopuxi30.data.models.RegResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthRegService {

    @POST("registration")
    suspend fun registration(@Body regBody: AuthRegBody): Response<RegResponse>

    @POST("auth")
    suspend fun authorization(@Body authBody: AuthRegBody): Response<AuthResponse>

}