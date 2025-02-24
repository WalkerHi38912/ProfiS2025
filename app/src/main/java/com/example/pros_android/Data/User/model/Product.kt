package com.example.pros_android.Data.User.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null,
    val name: String = "",
    val description: String = "",
    val price: String = "",
    @SerialName("stock_quantity")
    val stockQuantity: Int? = null,
    @SerialName("image_url")
    val imageUrl: String = ""
)
