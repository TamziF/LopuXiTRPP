package com.example.lopuxi30.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegResponse (
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String
)