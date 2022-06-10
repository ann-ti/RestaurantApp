package com.annti.restaurantapp.presentation.reviews.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annti.restaurantapp.R
import com.annti.restaurantapp.data.model.Reviews
import com.annti.restaurantapp.databinding.ItemReviewsBinding
import com.annti.restaurantapp.utils.dateFormat
import com.annti.restaurantapp.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class ReviewsAdapterDelegate :
    AbsListItemAdapterDelegate<Reviews, Reviews, ReviewsAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: Reviews,
        items: MutableList<Reviews>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_reviews))
    }

    override fun onBindViewHolder(
        item: Reviews,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemReviewsBinding.bind(view)

        fun bind(reviews: Reviews) {
            binding.userAndRestaurantName.text =
                "${reviews.userFIO} о ресторане ${reviews.restaurantName}"
            binding.message.text = reviews.message
            if (reviews.isPositive!!) {
                binding.imgIsPositive.setImageResource(R.drawable.ic_good)
            } else binding.imgIsPositive.setImageResource(R.drawable.ic_bad)
            binding.dateAdded.text = "${dateFormat(reviews.dateAdded)}"
        }
    }
}