package com.annti.restaurantapp.presentation.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annti.restaurantapp.data.model.Restaurant
import com.annti.restaurantapp.domain.RestaurantUseCase
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch

class RestaurantsViewModel(
    private val restaurantUseCase: RestaurantUseCase
) : ViewModel() {

    private var loadingLiveData = MutableLiveData<Boolean>()
    private var restaurantLiveData = MutableLiveData<List<Restaurant>>()
    private var errorData = LiveEvent<String>()
    private var errorViewData = LiveEvent<Boolean>()

    val loading: LiveData<Boolean>
        get() = loadingLiveData
    val restaurantList: LiveData<List<Restaurant>>
        get() = restaurantLiveData
    val error: LiveEvent<String>
        get() = errorData
    val errorView: LiveEvent<Boolean>
        get() = errorViewData

    fun getRestaurantsList() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                val result = restaurantUseCase.getRestaurantsList()
                restaurantLiveData.postValue(result)
                errorViewData.postValue(false)
            } catch (e: Throwable) {
                errorData.postValue("Нам не удалось обработать ваш запрос. Произошла ошибка: ${e.message}. Попробуйте еще раз")
                errorViewData.postValue(true)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
}