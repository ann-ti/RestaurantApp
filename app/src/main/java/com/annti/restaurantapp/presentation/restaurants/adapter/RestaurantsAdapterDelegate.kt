package com.annti.restaurantapp.presentation.restaurants.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annti.restaurantapp.R
import com.annti.restaurantapp.data.model.Restaurant
import com.annti.restaurantapp.databinding.ItemRestaurantBinding
import com.annti.restaurantapp.utils.inflate
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class RestaurantsAdapterDelegate :
    AbsListItemAdapterDelegate<Restaurant, Restaurant, RestaurantsAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: Restaurant,
        items: MutableList<Restaurant>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_restaurant))
    }

    override fun onBindViewHolder(
        item: Restaurant,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRestaurantBinding.bind(view)

        fun bind(restaurant: Restaurant) {
            binding.txtNameRest.text = restaurant.name
            binding.txtSpecializations.text = restaurant.specializations.joinToString {
                it.name
            }
            binding.txtPositiveReviews.text = "${restaurant.positiveReviews}%"
            binding.txtDeliveryTime.text = "${restaurant.deliveryTime} минут"
            Glide.with(itemView)
                .load(restaurant.logo)
                .placeholder(R.drawable.placeholder_restaurant)
                .into(binding.logoRest)
        }
    }
}
