package com.example.lopuxi30.data.models

import java.sql.Timestamp

data class Post(
    val id: Int,
    val author: String,
    val time: Timestamp,
    val text: String,
    val photos: ArrayList<String>
)
