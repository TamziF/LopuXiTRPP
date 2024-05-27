package com.example.lopuxi30.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRegBody (
    @SerialName("username")
    var username: String,
    @SerialName("password")
    var password: String
)