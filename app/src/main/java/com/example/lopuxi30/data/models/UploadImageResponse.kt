package com.example.lopuxi30.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadImageResponse (
    @SerialName("uri")
    val uri: String
)