package com.annti.restaurantapp.presentation.hits.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annti.restaurantapp.R
import com.annti.restaurantapp.data.model.Hits
import com.annti.restaurantapp.databinding.ItemHitsBinding
import com.annti.restaurantapp.utils.inflate
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class HitsAdapterDelegate :
    AbsListItemAdapterDelegate<Hits, Hits, HitsAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: Hits,
        items: MutableList<Hits>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_hits))
    }

    override fun onBindViewHolder(
        item: Hits,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class Holder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHitsBinding.bind(view)

        fun bind(hits: Hits) {
            binding.productName.text = hits.productName
            binding.productDescription.text = hits.productDescription
            Glide.with(itemView)
                .load(hits.productImage)
                .placeholder(R.drawable.placeholder_restaurant)
                .into(binding.productImage)

            Glide.with(itemView)
                .load(hits.restaurantLogo)
                .placeholder(R.drawable.placeholder_restaurant)
                .into(binding.restaurantLogo)
            binding.restaurantName.text = hits.restaurantName
            binding.productPrice.text = "${hits.productPrice} руб."
        }
    }
}
