package com.annti.restaurantapp.domain

import com.annti.restaurantapp.data.model.Hits
import com.annti.restaurantapp.data.model.Restaurant
import com.annti.restaurantapp.data.model.Reviews
import com.annti.restaurantapp.data.repository.RestaurantRepository

interface RestaurantUseCase {
    suspend fun getRestaurantsList(): List<Restaurant>
    suspend fun getHitsList(): List<Hits>
    suspend fun getReviewsList(): List<Reviews>
}

class RestaurantUseCaseImpl(
    private val restaurantRepository: RestaurantRepository
) : RestaurantUseCase {
    override suspend fun getRestaurantsList(): List<Restaurant> =
        restaurantRepository.getRestaurantsList()

    override suspend fun getHitsList(): List<Hits> =
        restaurantRepository.getHitsList()

    override suspend fun getReviewsList(): List<Reviews> =
        restaurantRepository.getReviewsList()
}