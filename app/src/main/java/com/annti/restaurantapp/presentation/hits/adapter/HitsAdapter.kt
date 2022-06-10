package com.annti.restaurantapp.presentation.hits.adapter

import androidx.recyclerview.widget.DiffUtil
import com.annti.restaurantapp.data.model.Hits
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HitsAdapter :
    AsyncListDifferDelegationAdapter<Hits>(HitsDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(HitsAdapterDelegate())
    }

    class HitsDiffUtilCallback : DiffUtil.ItemCallback<Hits>() {
        override fun areItemsTheSame(
            oldItem: Hits,
            newItem: Hits
        ): Boolean {
            return oldItem.productName == newItem.productName
        }

        override fun areContentsTheSame(
            oldItem: Hits,
            newItem: Hits
        ): Boolean {
            return oldItem == newItem
        }
    }
}