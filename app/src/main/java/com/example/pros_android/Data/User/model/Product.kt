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
    @SerialName("image_url_main")
    val imageUrlMain: String = "",
    @SerialName("image_url_1")
    val imageUrl1: String? = "",
    @SerialName("image_url_2")
    val imageUrl2: String? = "",
    @SerialName("image_url_3")
    val imageUrl3: String? = "",
    @SerialName("image_url_4")
    val imageUrl4: String? = ""
)
