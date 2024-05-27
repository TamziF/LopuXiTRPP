package com.example.lopuxi30.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostBody (
    @SerialName("author")
    var author: String,
    @SerialName("text")
    var text: String,
    @SerialName("photosID")
    val photosID: ArrayList<String>
)