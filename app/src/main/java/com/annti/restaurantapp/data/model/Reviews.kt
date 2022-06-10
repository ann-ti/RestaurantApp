package com.annti.restaurantapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reviews(
    @Json(name = "IsPositive")
    val isPositive: Boolean?,
    @Json(name = "Message")
    val message: String?,
    @Json(name = "DateAdded")
    val dateAdded: String?,
    @Json(name = "UserFIO")
    val userFIO: String?,
    @Json(name = "RestaurantName")
    val restaurantName: String?
)
