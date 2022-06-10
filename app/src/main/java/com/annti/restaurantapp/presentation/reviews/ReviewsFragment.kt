package com.annti.restaurantapp.presentation.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.annti.restaurantapp.R
import com.annti.restaurantapp.databinding.FragmentReviewsBinding
import com.annti.restaurantapp.presentation.reviews.adapter.ReviewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsFragment: Fragment(R.layout.fragment_reviews) {

    private val viewModel: ReviewsViewModel by viewModel()
    private lateinit var binding: FragmentReviewsBinding
    private val reviewsAdapter: ReviewsAdapter by lazy { ReviewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner) {
            binding.txtError.text = it
        }
        viewModel.errorView.observe(viewLifecycleOwner) { error ->
            if (error) {
                binding.frameAlarmError.visibility = View.VISIBLE
                binding.listReviews.visibility = View.GONE
            } else {
                binding.frameAlarmError.visibility = View.GONE
                binding.listReviews.visibility = View.VISIBLE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else binding.progressBar.visibility = View.GONE
        }
        setRecyclerViewListRestaurant()
        getRestaurantList()
    }

    private fun setRecyclerViewListRestaurant() {
        with(binding.listReviews) {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }

    private fun getRestaurantList() {
        viewModel.getReviewsList()
        viewModel.reviewsList.observe(viewLifecycleOwner){
            reviewsAdapter.items = it
        }
    }

}