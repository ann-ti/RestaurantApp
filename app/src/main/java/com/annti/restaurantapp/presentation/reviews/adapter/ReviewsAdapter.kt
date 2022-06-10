package com.annti.restaurantapp.presentation.reviews.adapter

import androidx.recyclerview.widget.DiffUtil
import com.annti.restaurantapp.data.model.Reviews
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ReviewsAdapter:
    AsyncListDifferDelegationAdapter<Reviews>(ReviewsDiffUtilCallback()) {

    init {
        delegatesManager
            .addDelegate(ReviewsAdapterDelegate())
    }

    class ReviewsDiffUtilCallback : DiffUtil.ItemCallback<Reviews>() {
        override fun areItemsTheSame(
            oldItem: Reviews,
            newItem: Reviews
        ): Boolean {
            return oldItem.isPositive == newItem.isPositive
        }

        override fun areContentsTheSame(
            oldItem: Reviews,
            newItem: Reviews
        ): Boolean {
            return oldItem == newItem
        }
    }
}