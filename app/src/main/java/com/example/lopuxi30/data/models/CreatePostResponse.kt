package com.example.lopuxi30.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("author")
    val author: String,
    @SerialName("text")
    val text: String,
    @SerialName("photos")
    val photos: ArrayList<String>
)
