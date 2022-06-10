package com.annti.restaurantapp.presentation.hits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annti.restaurantapp.data.model.Hits
import com.annti.restaurantapp.domain.RestaurantUseCase
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch

class HitsViewModel(
    private val restaurantUseCase: RestaurantUseCase
) : ViewModel() {

    private var loadingLiveData = MutableLiveData<Boolean>()
    private var hitsLiveData = MutableLiveData<List<Hits>>()
    private var errorData = LiveEvent<String>()
    private var errorViewData = LiveEvent<Boolean>()

    val loading: LiveData<Boolean>
        get() = loadingLiveData
    val hitsList: LiveData<List<Hits>>
        get() = hitsLiveData
    val error: LiveEvent<String>
        get() = errorData
    val errorView: LiveEvent<Boolean>
        get() = errorViewData

    fun getHitsList() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                val result = restaurantUseCase.getHitsList()
                hitsLiveData.postValue(result)
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