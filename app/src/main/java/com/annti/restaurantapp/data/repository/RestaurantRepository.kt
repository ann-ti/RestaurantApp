package com.annti.restaurantapp.data.repository

import com.annti.restaurantapp.data.model.Hits
import com.annti.restaurantapp.data.model.Restaurant
import com.annti.restaurantapp.data.model.Reviews
import com.annti.restaurantapp.data.network.RestaurantApi
import com.annti.restaurantapp.data.network.handleNetworkErrors

interface RestaurantRepository {
    suspend fun getRestaurantsList(): List<Restaurant>
    suspend fun getHitsList(): List<Hits>
    suspend fun getReviewsList(): List<Reviews>
}

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
) : RestaurantRepository {
    override suspend fun getRestaurantsList(): List<Restaurant> =
        handleNetworkErrors { restaurantApi.getRestaurantsList() }

    override suspend fun getHitsList(): List<Hits> =
        handleNetworkErrors { restaurantApi.getHitsList() }

    override suspend fun getReviewsList(): List<Reviews> =
        handleNetworkErrors { restaurantApi.getReviewsList() }

}