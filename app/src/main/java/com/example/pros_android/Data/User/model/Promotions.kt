package com.example.pros_android.Data.User.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Promotions(
    val id: Int = 0,
    @SerialName("created_at")
    val createdAt: String = "",
    val title: String = "",
    val description: String? = null,
    @SerialName("image_url")
    val imageUrl: String = ""
)
