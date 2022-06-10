package com.annti.restaurantapp.data.network

import com.annti.restaurantapp.data.model.Hits
import com.annti.restaurantapp.data.model.Restaurant
import com.annti.restaurantapp.data.model.Reviews
import retrofit2.http.GET

interface RestaurantApi {

    @GET("api/v1/restaurants")
    suspend fun getRestaurantsList(): List<Restaurant>

    @GET("api/v1/reviews")
    suspend fun getReviewsList(): List<Reviews>

    @GET("api/v1/hits")
    suspend fun getHitsList(): List<Hits>
}