package com.annti.restaurantapp.presentation.restaurants.adapter

import androidx.recyclerview.widget.DiffUtil
import com.annti.restaurantapp.data.model.Restaurant
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class RestaurantsAdapter :
    AsyncListDifferDelegationAdapter<Restaurant>(RestaurantsDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(RestaurantsAdapterDelegate())
    }

    class RestaurantsDiffUtilCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(
            oldItem: Restaurant,
            newItem: Restaurant
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Restaurant,
            newItem: Restaurant
        ): Boolean {
            return oldItem == newItem
        }
    }
}