package com.annti.restaurantapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Restaurant(
    @Json(name = "Name")
    val name: String?,
    @Json(name = "Logo")
    val logo: String?,
    @Json(name = "MinCost")
    val minCost: Long,
    @Json(name = "DeliveryCost")
    val deliveryCost: Long?,
    @Json(name = "DeliveryTime")
    val deliveryTime: Long?,
    @Json(name = "PositiveReviews")
    val positiveReviews: Long?,
    @Json(name = "ReviewsCount")
    val reviewsCount: Long?,
    @Json(name = "Specializations")
    val specializations: List<Specializations>
)

@JsonClass(generateAdapter = true)
data class Specializations(
    @Json(name = "Name")
    val name: String
)
