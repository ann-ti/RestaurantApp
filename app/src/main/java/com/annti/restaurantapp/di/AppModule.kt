package com.annti.restaurantapp.di

import com.annti.restaurantapp.data.network.RestaurantApi
import com.annti.restaurantapp.data.network.RetrofitFactory
import com.annti.restaurantapp.data.repository.RestaurantRepository
import com.annti.restaurantapp.data.repository.RestaurantRepositoryImpl
import com.annti.restaurantapp.domain.RestaurantUseCase
import com.annti.restaurantapp.domain.RestaurantUseCaseImpl
import com.annti.restaurantapp.presentation.hits.HitsViewModel
import com.annti.restaurantapp.presentation.restaurants.RestaurantsViewModel
import com.annti.restaurantapp.presentation.reviews.ReviewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit
import org.koin.androidx.viewmodel.dsl.viewModel

const val BASE_URL = "https://front-task.chibbistest.ru/"

val appModule = module {

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        RetrofitFactory(okHttpClient = get())
    }

    single {
        get<RetrofitFactory>().makeService<RestaurantApi>(BASE_URL)
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(
            restaurantApi = get()
        )
    }

    single<RestaurantUseCase> {
        RestaurantUseCaseImpl(
            restaurantRepository = get()
        )
    }

    viewModel {
        RestaurantsViewModel(
            restaurantUseCase = get()
        )
    }

    viewModel {
        HitsViewModel(
            restaurantUseCase = get()
        )
    }

    viewModel {
        ReviewsViewModel(
            restaurantUseCase = get()
        )
    }
}