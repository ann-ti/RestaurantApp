package com.annti.restaurantapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hits(
    @Json(name = "ProductName")
    val productName: String?,
    @Json(name = "ProductImage")
    val productImage: String?,
    @Json(name = "ProductPrice")
    val productPrice: Long?,
    @Json(name = "ProductDescription")
    val productDescription: String?,
    @Json(name = "RestaurantId")
    val restaurantId: Long?,
    @Json(name = "RestaurantName")
    val restaurantName: String?,
    @Json(name = "RestaurantLogo")
    val restaurantLogo: String?
)